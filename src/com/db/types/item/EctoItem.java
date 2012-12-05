package com.db.types.item;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.log4j.Logger;

import com.db.DB;
import com.db.types.BaseTable;

@Entity
@Table(name = "ECTO_ITEM")
public class EctoItem extends ItemTable<EctoItem> {
   private static final Logger LOGGER = Logger.getLogger(EctoItem.class);
   public void setAvg() {
         setAvgOffer((getOfferPrice() + getAvgOffer()) /2);
         setAvgSale((getSalePrice() + getAvgSale()) /2);     
   }
   
   public boolean duplicate() {
      EctoItem prev = getPrevious();
      if(prev != null && prev.getOfferPrice() == getOfferPrice() && prev.getSalePrice() == getSalePrice()) {
         LOGGER.info(String.format(
               "Duplicate Item found at sale: %s\noffer %s\ndate:\n",
               getSalePrice(), getOfferPrice(), getRecordedDate()));
         return true;
      }
      return false;
   }

   @Override
   public Class<EctoItem> getThisClass() {
      return EctoItem.class;
   }
   
}
