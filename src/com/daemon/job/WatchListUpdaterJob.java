package com.daemon.job;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.core.Utils;
import com.db.BuyOrderDAO;
import com.db.types.BuyOrder;
import com.db.types.watchlist.WatchList;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.types.response.Item;

public class WatchListUpdaterJob extends DaemonJob {

   private static final int MAX = 100;
   private static final int BUFFER = 1;
   private static final String TABLE = "WATCH_LIST";

   private static final String SQL = String
         .format(
               "SELECT bx.* from %s as bx left join %s o on bx.id = o.%s "
                     + "WHERE o.id IS NULL AND DATEDIFF(hour,bx.%s,GETDATE())> %s ORDER BY bx.%s DESC",
               TABLE, TABLE, "watch_list_id", "last_updated", BUFFER,
               "last_updated");

   private static final String UPDATE = String.format(
         "update WatchList SET %s = %s WHERE %s IN(%s)", "last_updated",
         "GETDATE()", "id", ":ids");

   public WatchListUpdaterJob() {
   }

   @Override
   public void execute() {
      Session s = db.createSession();
      Transaction tr = s.beginTransaction();
      try {

         List<WatchList> items = s.createSQLQuery(SQL).addEntity(WatchList.class).setMaxResults(MAX)
               .list();
         // Make call to Utils to get Item ojects
         if (items.size() > 0) {
            List<Integer> ids = Lists.transform(items, WatchList.ID_FN);
            final Map<Integer, WatchList> itemIds = Maps.uniqueIndex(items,
                  new Function<WatchList, Integer>() {

                     public Integer apply(WatchList l) {
                        return l.getItemId();
                     }
                  });
            List<Item> newVal = Utils.requestItems(itemIds.keySet());
            Collection<Integer> buyOrderIds = BuyOrderDAO.inBuyOrder(itemIds.keySet());
            for (Item in : newVal) {
               WatchList w = new WatchList();
               WatchList prev = itemIds.get(in.getData_id());
               w.setAmount(1);
               w.setOfferPrice(in.getMax_offer_unit_price());
               w.setSalePrice(in.getMin_sale_unit_price());
               w.setRecordedDate(prev.getRecordedDate());
               w.setItemId(in.getData_id());
               w.setItemName(in.getName());
               w.setLastUpdated(new Date());
               w.setWatchListId( prev != null ? prev : null);
               w.setChangeBuyPriceHour(in.getOffer_price_change_last_hour());
               w.setChangeSellPriceHour(in.getSale_price_change_last_hour());
               w.setSaleAmount(in.getSale_availability());
               w.setOfferAmount(in.getOffer_availability());
               System.out.println("Updating " + w.getItemName());
               if(!buyOrderIds.contains(w.getItemId())) {
                  System.out.println("Submitting buy order for: " + w.getItemName());
                  BuyOrder o = new BuyOrder();
                  o.setAmount(1);
                  o.setBuyDate(new Date());
                  o.setItemId(w.getItemId());
                  o.setItemName(w.getItemName());
                  o.setPricePerItem(w.getOfferPrice());
                  o.setTotal(o.getPricePerItem() * o.getAmount());
                  s.persist(o);
               }
               s.persist(w);
            }

            s.createQuery(UPDATE).setParameterList("ids", ids).executeUpdate();
            tr.commit();
         }
      } catch (Exception e) {
         e.printStackTrace();
         tr.rollback();
      }
   }

}
