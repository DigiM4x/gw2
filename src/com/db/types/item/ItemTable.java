package com.db.types.item;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.db.types.BaseTable;

@MappedSuperclass
public abstract class ItemTable<E> extends BaseTable<E> {
   @Column(name = "average_sale")
   protected int avgSale;
   
   @Column(name = "average_offer")
   protected int avgOffer;
   
   public int getAvgSale() {
      return avgSale;
   }

   public void setAvgSale(int avgSale) {
      this.avgSale = avgSale;
   }

   public int getAvgOffer() {
      return avgOffer;
   }

   public void setAvgOffer(int avgOffer) {
      this.avgOffer = avgOffer;
   }
}
