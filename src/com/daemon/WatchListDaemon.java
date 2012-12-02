package com.daemon;

import java.util.Scanner;

import com.daemon.job.DaemonJob;

public class WatchListDaemon extends Daemon {
   public static void main(String[] args) {
      System.out.println("Starting WatchListDaemon press any key to stop");
      new Thread(new WatchListDaemon()).start();
      Scanner scan = new Scanner(System.in);
      scan.next();
   }

   @Override
   public void run() {
      try {
         DaemonJob[] jobs = getJobs(getClass().getSimpleName() + ".cfg");
         executeJobs(jobs);
      }catch(Exception e) {
         System.out.println("ERROR IN DAEMON QUITTING");
         e.printStackTrace();
         System.exit(2);
      }
   }
}
