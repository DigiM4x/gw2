package com.types.response;

import java.net.URL;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.db.types.WatchList;
import com.google.common.base.Function;

public class Item {
   private int data_id;
   private String name;
   private int rarity;
   private int restriction_level;
   private URL img;
   private int type_id;
   private int sub_type_id;
   private Date price_last_changed;
   private int max_offer_unit_price;
   private int min_sale_unit_price;
   private int offer_availability;
   private int gw2db_external_id;
   private int sale_price_change_last_hour;
   private int offer_price_change_last_hour;
   private int sale_availability;
   public int getData_id() {
      return data_id;
   }
   public void setData_id(int data_id) {
      this.data_id = data_id;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public int getRarity() {
      return rarity;
   }
   public void setRarity(int rarity) {
      this.rarity = rarity;
   }
   public int getRestriction_level() {
      return restriction_level;
   }
   public void setRestriction_level(int restriction_level) {
      this.restriction_level = restriction_level;
   }
   public URL getImg() {
      return img;
   }
   public void setImg(URL img) {
      this.img = img;
   }
   public int getType_id() {
      return type_id;
   }
   public void setType_id(int type_id) {
      this.type_id = type_id;
   }
   public int getSub_type_id() {
      return sub_type_id;
   }
   public void setSub_type_id(int sub_type_id) {
      this.sub_type_id = sub_type_id;
   }
   public Date getPrice_last_changed() {
      return price_last_changed;
   }
   public void setPrice_last_changed(String price_last_changed) {
         this.price_last_changed = DateTime.parse(price_last_changed.replaceAll(" UTC", ""),
               DateTimeFormat.forPattern("YY-MM-dd HH:mm:ss")).toDate();
   }
   public int getMax_offer_unit_price() {
      return max_offer_unit_price;
   }
   public void setMax_offer_unit_price(int max_offer_unit_price) {
      this.max_offer_unit_price = max_offer_unit_price;
   }
   public int getMin_sale_unit_price() {
      return min_sale_unit_price;
   }
   public void setMin_sale_unit_price(int min_sale_unit_price) {
      this.min_sale_unit_price = min_sale_unit_price;
   }
   public int getOffer_availability() {
      return offer_availability;
   }
   public void setOffer_availability(int offer_availability) {
      this.offer_availability = offer_availability;
   }
   public int getGw2db_external_id() {
      return gw2db_external_id;
   }
   public void setGw2db_external_id(int gw2db_external_id) {
      this.gw2db_external_id = gw2db_external_id;
   }
   public int getSale_price_change_last_hour() {
      return sale_price_change_last_hour;
   }
   public void setSale_price_change_last_hour(int sale_price_change_last_hour) {
      this.sale_price_change_last_hour = sale_price_change_last_hour;
   }
   public int getOffer_price_change_last_hour() {
      return offer_price_change_last_hour;
   }
   public void setOffer_price_change_last_hour(int offer_price_change_last_hour) {
      this.offer_price_change_last_hour = offer_price_change_last_hour;
   }
   public int getSale_availability() {
      return sale_availability;
   }
   public void setSale_availability(int sale_availability) {
      this.sale_availability = sale_availability;
   }
   
   /*public static final Function<Item,WatchList> TO_WL = new Function<Item,WatchList>(){
      
      public WatchList apply(Item in) {
         
      }
   };*/
   public final WatchList toWatchList() {
      WatchList w = new WatchList();
      w.setAmount(1);
      w.setCurrentBuyAmount(getMax_offer_unit_price());
      w.setCurrentSellAmount(getMin_sale_unit_price());
      w.setDateAdded(new Date());
      w.setItemId(getData_id());
      w.setItemName(getName());
      w.setLastUpdated(new Date());
      w.setChangeBuyPriceHour(getOffer_price_change_last_hour());
      w.setChangeSellPriceHour(getSale_price_change_last_hour());
      w.setSellAvailable(getSale_availability());
      w.setBuyAvailable(getOffer_availability());
      return w;
   }
   
}
