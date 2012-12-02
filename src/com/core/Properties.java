package com.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
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
         PropertiesConfiguration cfg = new PropertiesConfiguration("conf\\application.properties");
         PropertiesConfiguration cfg2 = new PropertiesConfiguration("conf\\application.default.properties");
         loadProperties(cfg2);
         loadProperties(cfg);      
      } catch (ConfigurationException e) {
         LOGGER.error("Exception while reading properties, using defaults");
         e.printStackTrace();
      }
      
   }
   private static void loadProperties(Configuration cfg) {
      if(!cfg.isEmpty()) {
         Iterator<String> iter = cfg.getKeys();
         while(iter.hasNext()) {
            String key = iter.next();
            prop.put(key, cfg.getString(key));
            LOGGER.info("Loading property: " + key + "=" + cfg.getString(key));
         }
      }
   }
   public static String getProperty(String key) {
      return prop.get(key);
   }
}
