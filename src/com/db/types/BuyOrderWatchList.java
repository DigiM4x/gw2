package com.db.types;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BUY_ORDER_WATCH_LIST")
public class BuyOrderWatchList {
   
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
   
   @Column(name = "current_buy_price")
   private int currentBuyAmount;
   
   @Column(name = "current_sell_price")
   private int currentSellAmount;
   
   @Column(name = "possible_profit")
   private Integer possibleProfit;
   
   @OneToOne
   @JoinColumn(name = "buy_order_id", referencedColumnName="id")
   private BuyOrder buyOrder;
   
   @Column(name = "last_updated")
   private Date lastUpdated;
   
   @Column(name = "sell_available")
   private int sellAvailable;
   
   @Column(name = "buy_available")
   private int buyAvailable;
   
   @Column(name = "buy_change_since_order")
   private int buyChange;
   
   @Column(name = "sell_change_since_order")
   private int sellChange;
   
   
   public int getBuyChange() {
      return buyChange;
   }

   public void setBuyChange(int buyChange) {
      this.buyChange = buyChange;
   }

   public int getSellChange() {
      return sellChange;
   }

   public void setSellChange(int sellChange) {
      this.sellChange = sellChange;
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

   public BuyOrder getBuyOrder() {
      return buyOrder;
   }

   public void setBuyOrder(BuyOrder buyOrder) {
      this.buyOrder = buyOrder;
   }

   public Date getLastUpdated() {
      return lastUpdated;
   }

   public void setLastUpdated(Date lastUpdated) {
      this.lastUpdated = lastUpdated;
   }

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
   
   
}
