package com.daemon;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;

import com.daemon.job.DaemonJob;

public abstract class Daemon implements Runnable {
   
   protected DaemonJob[] getJobs(String fileName) throws Exception {
      List<DaemonJob> jobs = new ArrayList<DaemonJob>();
      Scanner scan = new Scanner(new File(fileName));
      while(scan.hasNextLine()) {
         String className = scan.nextLine();
         if (!className.startsWith("#") && className.length() > 0) {
            String wait = scan.nextLine();
            Object o = Class.forName(className).getConstructors()[0]
                  .newInstance(new Object[] {});
            DaemonJob b = (DaemonJob) o;
            b.setWait(Integer.valueOf(wait));
            jobs.add(b);
         }
      }
      return jobs.toArray(new DaemonJob[]{});
   }
   
   protected MyScheduler pool;
   protected void executeJobs(DaemonJob[] ar) {
      pool = new MyScheduler(1);
      for(DaemonJob a : ar) {
         pool.scheduleAtFixedRate(a, 0, a.getWait(), TimeUnit.MILLISECONDS);
      }
      
   }
   private class MyScheduler extends ScheduledThreadPoolExecutor {
      public MyScheduler(int size) {
         super(size);
      }
      @Override
      public void beforeExecute(Thread t, Runnable r) {
       //  System.out.println(String.format("[%s]Running " + ((ScheduledFutureTask)r),new DateTime().toString("HH:mm:ss")));
         super.beforeExecute(t, r);
      }
   }
}
