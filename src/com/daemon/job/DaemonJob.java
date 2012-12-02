package com.daemon.job;

import org.springframework.beans.factory.annotation.Autowired;

import com.db.DB;

public abstract class DaemonJob implements Runnable{
   private int wait;
   
   @Autowired
   protected DB db;
   public DaemonJob() {
      
   }
   
   public int getWait() {
      return wait;
   }

   public void setWait(int wait) {
      this.wait = wait;
   }
   
}
