package com.daemon.job;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.db.types.BuyOrderWatchList;

public class BuyOrderWatchListUpdaterJob extends DaemonJob{

   private static final String SQL = 
        " select * from buy_order_watch_list l inner join (select distinct item_id,MAX(last_updated) 'maxD' from buy_order_watch_list group by item_id) l2 " +
        "on l2.item_id = l.item_id and l.last_updated = l2.maxD";
   @Override
   public void run() {
      Session s = db.createSession();
      Transaction tr = s.beginTransaction();
      try {
         List<BuyOrderWatchList> items = s.createSQLQuery(SQL).addEntity(BuyOrderWatchList.class).list();
         
      }catch(Exception e) {
         tr.rollback();
      }
   }

}
