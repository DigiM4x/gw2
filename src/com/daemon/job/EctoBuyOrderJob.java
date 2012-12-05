package com.daemon.job;


import org.apache.log4j.Logger;

import com.daemon.job.item.ItemBuyOrder;
import com.db.types.item.EctoItem;

public class EctoBuyOrderJob extends ItemBuyOrder<EctoItem> {
   
   private static final Logger LOGGER = Logger.getLogger(EctoBuyOrderJob.class);
   @Override
   public void execute(EctoItem item) {
      int sale = item.getAvgSale();
      int buy = item.getOfferPrice();
      double percent = Math.max(sale, item.getSalePrice()) * .17;
      double tsale = sale + percent;
      String what = "";
      if(tsale < buy) {
         what = "Buying";
      }else {
         what = "Not Buying";
      }
      
      System.out.println("[" + what + "]: ectos at buy price: " + buy + " sale price: " + item.getSalePrice());
      LOGGER.info("[" + what + "]: ectos at buy price: " + buy + " sale price: " + item.getSalePrice());
   }



}
