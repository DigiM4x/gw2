package com.db;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Session;

import com.db.types.BuyOrder;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class BuyOrderDAO {
   private static DB db = DB.getDB();
   private static final int BUFFER = 5;
   private static final String SQL = "from BuyOrder where datediff(day,buy_date,GETDATE()) < :buffer";
   private static final String SQL2 = SQL + " AND item_id in (:ids)";
   public static BuyOrder getBuyOrder(int itemId) {
      Session s = db.createSession();
      List<BuyOrder> orders = s.createQuery(SQL)
            .setParameter("buffer", BUFFER).list();

      return orders.size() == 0 ? null : orders.get(0);
   }
   
   @SuppressWarnings("unchecked")
   public static Collection<Integer> inBuyOrder(Collection<Integer> itemIds) {
      List<Integer> inDB =
            Lists.transform(db.createSession().createQuery(SQL2).setParameter("buffer", BUFFER).setParameterList("ids", itemIds).list(),
                  new Function<BuyOrder,Integer>(){
               
               public Integer apply(BuyOrder o) {
                  return o.getItemId();
               }
            });
      return CollectionUtils.intersection(itemIds, inDB);
      
   }
}
