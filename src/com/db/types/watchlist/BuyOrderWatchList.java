package com.db.types.watchlist;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.db.types.BuyOrder;
import com.db.types.WatchListTable;

@Entity
@Table(name = "BUY_ORDER_WATCH_LIST")
public class BuyOrderWatchList extends WatchListTable<BuyOrderWatchList>{
   
   
   @Column(name = "possible_profit")
   private Integer possibleProfit;
   
   @OneToOne
   @JoinColumn(name = "buy_order_id", referencedColumnName="id")
   private BuyOrder buyOrder;
   
   @Column(name = "last_updated")
   private Date lastUpdated;
   

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

   @Override
   public Class<BuyOrderWatchList> getThisClass() {
      return BuyOrderWatchList.class;
   } 
   
}
