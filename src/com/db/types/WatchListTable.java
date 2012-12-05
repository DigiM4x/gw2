package com.db.types;

import javax.persistence.Column;


public abstract class WatchListTable<E> extends BaseTable<E> {

   @Column(name = "item_name")
   private String itemName;
   
   @Column(name = "amount")
   private int amount;
   
   @Column(name = "item_id")
   private int itemId;
   
   @Column(name = "buy_price_change_last_hour")
   private Integer changeBuyPriceHour;
   
   @Column(name = "sell_price_change_last_hour")
   private Integer changeSellPriceHour;

   public String getItemName() {
      return itemName;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public int getAmount() {
      return amount;
   }

   public void setAmount(int amount) {
      this.amount = amount;
   }

   public int getItemId() {
      return itemId;
   }

   public void setItemId(int itemId) {
      this.itemId = itemId;
   }

   public Integer getChangeBuyPriceHour() {
      return changeBuyPriceHour;
   }

   public void setChangeBuyPriceHour(Integer changeBuyPriceHour) {
      this.changeBuyPriceHour = changeBuyPriceHour;
   }

   public Integer getChangeSellPriceHour() {
      return changeSellPriceHour;
   }

   public void setChangeSellPriceHour(Integer changeSellPriceHour) {
      this.changeSellPriceHour = changeSellPriceHour;
   }
   
   
}
