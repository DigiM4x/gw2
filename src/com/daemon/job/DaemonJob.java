package com.daemon.job;

import org.springframework.beans.factory.annotation.Autowired;

import com.db.DB;

public abstract class DaemonJob implements Runnable{
   private int wait;
   
   protected DB db = DB.getDB();
   public DaemonJob() {
      
   }
   
   public int getWait() {
      return wait;
   }

   public void setWait(int wait) {
      this.wait = wait;
   }
   
   @Override
   public void run() {
      try{
         execute();
      }catch(Exception e) {
         e.printStackTrace();
      }
   }
   public abstract void execute();
   
}
