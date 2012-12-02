package com.db;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.stereotype.Component;

import com.core.Properties;

@Component
public class DB {
   private SessionFactory factory;
   private ServiceRegistry serviceRegistry;
   private static DB db;
   private DB() {}
   public static DB getDB(){
      synchronized (DB.class) {
         if (db != null)
            return db;
         DB db = new DB();
         Configuration configuration = new Configuration();
         configuration.configure(
               new File(Properties.getProperty(Properties.HIBERNATE_PATH))).setProperty("hibernate.show_sql", "false");
         ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
               .applySettings(configuration.getProperties())
               .buildServiceRegistry();
         SessionFactory factory = configuration
               .buildSessionFactory(serviceRegistry);
         db.factory = factory;
         db.serviceRegistry = serviceRegistry;
         return db;
      }
   }
   public Session createSession() {
      return factory.openSession();
      
      
   }
}
