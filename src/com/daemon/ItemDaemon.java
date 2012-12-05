package com.daemon;

import java.util.Scanner;

import com.daemon.job.DaemonJob;

public class ItemDaemon extends Daemon{

   public static void main(String[] args) {
      System.out.println("Starting WatchListDaemon press any key to stop");
      new Thread(new ItemDaemon()).start();
      Scanner scan = new Scanner(System.in);
      scan.next();
   }

   @Override
   public void run() {
      try {
         DaemonJob[] jobs = getJobs("conf\\daemon\\" + getClass().getSimpleName() + ".cfg");
         if(jobs.length ==0) {
            throw new Exception("No daemon jobs found!");
         }
         executeJobs(jobs);
      }catch(Exception e) {
         System.out.println("ERROR IN DAEMON QUITTING");
         e.printStackTrace();
         System.exit(2);
      }
   }

}
