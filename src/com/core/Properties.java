package com.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

public class Properties {
   public static final String HIBERNATE_PATH = "hibernate.path";
   public static final String URL = "tp.url";
   public static final String VERSION = "spidy.version";
   private static final Map<String,String> prop = new HashMap<String,String>();
   private static final Logger LOGGER = Logger.getLogger(Properties.class);
   static {
      try {
         PropertiesConfiguration cfg = new PropertiesConfiguration("application.properties");
         prop.put(HIBERNATE_PATH, "hibernate.cfg.xml");
         prop.put(VERSION, "v0.9");
         prop.put(URL, "http://www.gw2spidy.com");
         if(!cfg.isEmpty()) {
            Iterator<String> iter = cfg.getKeys();
            while(iter.hasNext()) {
               String key = iter.next();
               prop.put(key, cfg.getString(key));
               LOGGER.info("Loading property: " + key + "=" + cfg.getString(key));
            }
         }
      } catch (ConfigurationException e) {
         LOGGER.error("Exception while reading properties, using defaults");
         e.printStackTrace();
      }
      
   }
   public static String getProperty(String key) {
      return prop.get(key);
   }
}
