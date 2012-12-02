package com.db.types;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.common.base.Function;
import com.gui.CoreTableModel;

@Entity
@Table(name = "WATCH_LIST")
public class WatchList implements CoreTableModel {

   
   @Id
   @GeneratedValue
   @Column(name="id")
   private int id;
   
   @Column(name = "item_name")
   private String itemName;
   
   @Column(name = "amount")
   private int amount;
   
   @Column(name = "item_id")
   private int itemId;
   
   @Column(name = "date_added")
   private Date dateAdded;
   
   @Column(name = "current_buy_price")
   private int currentBuyAmount;
   
   @Column(name = "current_sell_price")
   private int currentSellAmount;
   
   @Column(name = "possible_profit")
   private Integer possibleProfit;
   
   @OneToOne
   @JoinColumn(name = "watch_list_id", referencedColumnName="id")
   private WatchList watchListId;
   
   @Column(name = "last_updated")
   private Date lastUpdated;

   @Column(name = "buy_price_change_last_hour")
   private Integer changeBuyPriceHour;
   
   @Column(name = "sell_price_change_last_hour")
   private Integer changeSellPriceHour;
   
   @Column(name = "sell_available")
   private int sellAvailable;
   
   @Column(name = "buy_available")
   private int buyAvailable;
   
   
   public int getSellAvailable() {
      return sellAvailable;
   }

   public void setSellAvailable(int sellAvailable) {
      this.sellAvailable = sellAvailable;
   }

   public int getBuyAvailable() {
      return buyAvailable;
   }

   public void setBuyAvailable(int buyAvailable) {
      this.buyAvailable = buyAvailable;
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

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

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

   public Date getDateAdded() {
      return dateAdded;
   }

   public void setDateAdded(Date dateAdded) {
      this.dateAdded = dateAdded;
   }

   public int getCurrentBuyAmount() {
      return currentBuyAmount;
   }

   public void setCurrentBuyAmount(int currentBuyAmount) {
      this.currentBuyAmount = currentBuyAmount;
   }

   public int getCurrentSellAmount() {
      return currentSellAmount;
   }

   public void setCurrentSellAmount(int currentSellAmount) {
      this.currentSellAmount = currentSellAmount;
   }

   public Integer getPossibleProfit() {
      return possibleProfit;
   }

   public void setPossibleProfit(Integer possibleProfit) {
      this.possibleProfit = possibleProfit;
   }
   public Date getLastUpdated() {
      return lastUpdated;
   }

   public void setLastUpdated(Date lastUpdated) {
      this.lastUpdated = lastUpdated;
   }

   public WatchList getWatchListId() {
      return watchListId;
   }

   public void setWatchListId(WatchList watchListId) {
      this.watchListId = watchListId;
   }
   @Override
   public String[] getColumns() {
      return new String[]{"ItemName","Amount","DateAdded","CurrentBuyPrice","CurrentSellPrice"};
   }   
   
   public static final Function<WatchList,Integer> ID_FN = new Function<WatchList, Integer>() {
      @Override
      public Integer apply(WatchList input) {
         return input.getId();
      }
   };

}
