package com.db.types.watchlist;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.db.types.WatchListTable;
import com.google.common.base.Function;
import com.gui.CoreTableModel;

@Entity
@Table(name = "WATCH_LIST")
public class WatchList extends WatchListTable<WatchList> implements CoreTableModel {


   @Column(name = "possible_profit")
   private Integer possibleProfit;
   
   @OneToOne
   @JoinColumn(name = "watch_list_id", referencedColumnName="id")
   private WatchList watchListId;
   
   @Column(name = "last_updated")
   private Date lastUpdated;
   
   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
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

   @Override
   public Class<WatchList> getThisClass() {
      return WatchList.class;
   }

}
