package com.daemon.job;

import java.util.Date;

import com.db.types.item.EctoItem;
import com.types.response.Item;

public class EctoItemWatcherJob extends ItemWatcher<EctoItem, EctoBuyOrderJob>{

   @Override
   public EctoItem evaluate(Item tradeInfo) {
      EctoItem i = new EctoItem();
      i.setOfferPrice(tradeInfo.getMax_offer_unit_price());
      i.setSalePrice(tradeInfo.getMin_sale_unit_price());
      i.setOfferAmount(tradeInfo.getOffer_availability());
      i.setSaleAmount(tradeInfo.getSale_availability());
      EctoItem prev = i.getPrevious();
      if(prev != null) {
         i.setAvgOffer(prev.getAvgOffer());
         i.setAvgSale(prev.getAvgSale());
      } else {
         i.setAvgOffer(i.getOfferPrice());
         i.setAvgSale(i.getSalePrice());
      }
      i.setAvg();
      i.setRecordedDate(new Date());
      if(i.duplicate()) {
         System.out.println("[" + new Date().toString() + "] returning null");
         return null;
      }
      return i;
   }

   @Override
   public int getItemId() {
      return 19721;
   }

   @Override
   public EctoBuyOrderJob getBuyOrderJob() {
      return new EctoBuyOrderJob();
   }
}
