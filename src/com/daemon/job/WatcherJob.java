package com.daemon.job;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.joda.time.DateTime;
import org.joda.time.Days;

import com.core.Utils;
import com.db.DB;
import com.db.types.WatchList;
import com.types.APIRequest;
import com.types.response.APIResponse;
import com.types.response.Item;

public class WatcherJob extends DaemonJob {

   private PrintWriter wr;
   private List<Integer> itemIds = new ArrayList<Integer>();

   private static final DateTime NOW = new DateTime();
   private static final int DAYS = 15;
   private Map<String, String> params = new HashMap<String, String>();

   public WatcherJob() throws Exception {
      File f = new File("WD.txt");
      if (!f.exists()) {
         f.createNewFile();
      } else {
         BufferedReader r = new BufferedReader(new InputStreamReader(
               new FileInputStream(f)));
         String t;
         while ((t = r.readLine()) != null) {
            String[] data = t.split(";");
            DateTime added = new DateTime(data[0]);
            if (Days.daysBetween(added, NOW).getDays() < DAYS) {
               itemIds.add(Integer.parseInt(data[1]));
            } else {
               System.out.println("toolate");
            }
         }
         r.close();
      }
      params.put("sort_trending", "sale");
      wr = new PrintWriter(f);
   }

   

   @Override
   public void run() {
      Item[] items = Utils.callAPI(
            new APIRequest(Arrays.asList("items", "all", "0"), params),APIResponse.class)
            .getResults();
      
      Session s = db.createSession();
      Transaction tr = s.beginTransaction();
      try {
         for (Item i : items) {
           
            if (!itemIds.contains(i.getData_id())) {
               System.out.println("Watching " + i.getName());
               s.persist(addItem(i));
            }
         }
         tr.commit();
      } catch (Exception e) {
         e.printStackTrace();
         tr.rollback();
      }

   }

   private WatchList addItem(Item i) {
      wr.println(NOW.toString() + ";" + i.getData_id());
      wr.flush();
      itemIds.add(i.getData_id());
      return i.toWatchList();
   }

}
