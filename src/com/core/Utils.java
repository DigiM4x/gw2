package com.core;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.types.APIRequest;
import com.types.ItemAPIRequest;
import com.types.response.APIResponse;
import com.types.response.Item;
import com.types.response.ItemAPIResponse;

public class Utils {
   public static final String VERSION =  Properties.getProperty(Properties.VERSION);
   public static final String SITE = Properties.getProperty(Properties.URL);
   public static final String FORMAT = "json";
   public static final String TIME_OFFSET = "-0700";

   public static String buildUrl(APIRequest r) {
      String url = SITE + "/" + VERSION + "/" + FORMAT;
      for (String t : r.getArguements()) {
         url += "/" + t;
      }
      Map<String, String> params = r.getParameters();
      if (params != null) {
         url += "?";
         Iterator<String> paramIter = params.keySet().iterator();
         while (paramIter.hasNext()) {
            String next = paramIter.next();
            url += next + "=" + params.get(next);
            if (paramIter.hasNext()) {
               url += "&";
            }
         }
      }
      return url;
   }

   public static Item callAPI(ItemAPIRequest r) {
      try {

         ObjectMapper map = new ObjectMapper();
        ItemAPIResponse rs;
         rs = map.readValue(getInputStream(r), ItemAPIResponse.class);
         System.out.println(rs.getResult().getData_id());
         return rs.getResult();
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }
   public static APIResponse callAPI(APIRequest r, Class cast) {

      try {

         ObjectMapper map = new ObjectMapper();
         APIResponse rs;
         rs = map.readValue(getInputStream(r), cast);
         System.out.println(rs.getResults()[0].getData_id());
         return rs;
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }

   private static InputStream getInputStream(APIRequest r) {
      try {
         URL url = new URL(buildUrl(r));
         URLConnection connection = url.openConnection();
         connection.setDoInput(true);
         connection.addRequestProperty("User-Agent", "Mozilla/5.0");
         return connection.getInputStream();
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }

   public static List<Item> requestItems(Collection<Integer> itemIds) {
      List<Item> items = new ArrayList<Item>();
      for (Integer i : itemIds) {
         items.add(callAPI(new ItemAPIRequest(i)));       
      }
      return items;
   }
}
