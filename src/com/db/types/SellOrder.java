package com.db.types;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gui.CoreTableModel;

@Entity
@Table(name = "SELL_ORDER")
public class SellOrder implements CoreTableModel{

   @Id
   @GeneratedValue
   @Column(name="id")
   private int id;
   
   @Column(name = "item_name")
   private String itemName;
   
   @Column(name = "amount")
   private int amount;
   
   @Column(name = "total")
   private int total;
   
   @Column(name = "price_per_item")
   private int pricePerItem;
   
   @Column(name = "sell_date")
   private Date buyDate;
   
   @Column(name = "item_id")
   private int itemId;
   
   @Column(name = "ah_cut")
   private int ahCut;
   
   @OneToOne
   @JoinColumn(name = "buy_order_id", referencedColumnName="id")
   private BuyOrder buyOrder;
   
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


   public int getTotal() {
      return total;
   }


   public void setTotal(int total) {
      this.total = total;
   }


   public int getPricePerItem() {
      return pricePerItem;
   }


   public void setPricePerItem(int pricePerItem) {
      this.pricePerItem = pricePerItem;
   }


   public Date getBuyDate() {
      return buyDate;
   }


   public void setBuyDate(Date buyDate) {
      this.buyDate = buyDate;
   }


   public int getItemId() {
      return itemId;
   }


   public void setItemId(int itemId) {
      this.itemId = itemId;
   }


   public int getAhCut() {
      return ahCut;
   }


   public void setAhCut(int ahCut) {
      this.ahCut = ahCut;
   }


   public BuyOrder getBuyOrder() {
      return buyOrder;
   }


   public void setBuyOrder(BuyOrder buyOrder) {
      this.buyOrder = buyOrder;
   }


   public int getBuyOrderId() {
      return buyOrder.getId();
   }


   @Override
   public String[] getColumns() {
      return new String[] {"ItemName","Amount","Total","SellDate","BuyOrderId"};
   }

}
