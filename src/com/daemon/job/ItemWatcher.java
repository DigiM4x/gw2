package com.daemon.job;



import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.core.Utils;
import com.daemon.job.item.ItemBuyOrder;
import com.db.DB;
import com.db.types.BaseTable;
import com.types.ItemAPIRequest;
import com.types.response.Item;

public abstract class ItemWatcher<V extends BaseTable<V>, Q extends ItemBuyOrder<V>> extends DaemonJob {
   public abstract V evaluate(Item tradeInfo);
   public abstract int getItemId();
   public abstract Q getBuyOrderJob();
   protected static final DB db = DB.getDB();

   @Override
   public void execute() {
      V item = evaluate(callAPI(getItemId()));
      if (item != null) {
         Session s = db.createSession();
         Transaction tr = s.beginTransaction();
         try {
            getBuyOrderJob().execute(item);
            s.persist(item);
            tr.commit();
         } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
         }
      }
   }
   protected Item callAPI(int itemId) {
      return Utils.callAPI(new ItemAPIRequest(itemId));
   }
   
}
