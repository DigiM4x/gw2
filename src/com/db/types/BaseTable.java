package com.db.types;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.criterion.Order;

import com.db.DB;

@MappedSuperclass
public abstract class BaseTable<E> {
   
   @Id
   @GeneratedValue
   @Column(name = "id")   
   protected int id;
   
   @Column(name = "offer_price")
   protected int offerPrice;
   
   @Column(name = "sale_price")
   protected int salePrice;
   
   @Column(name = "offer_amount")
   protected int offerAmount;
   
   @Column(name = "sale_amount")
   protected int saleAmount;
   
   @Column(name = "recorded_date")
   protected Date recordedDate;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getOfferPrice() {
      return offerPrice;
   }

   public void setOfferPrice(int offerPrice) {
      this.offerPrice = offerPrice;
   }

   public int getSalePrice() {
      return salePrice;
   }

   public void setSalePrice(int salePrice) {
      this.salePrice = salePrice;
   }

   public int getOfferAmount() {
      return offerAmount;
   }

   public void setOfferAmount(int offerAmount) {
      this.offerAmount = offerAmount;
   }

   public int getSaleAmount() {
      return saleAmount;
   }

   public void setSaleAmount(int saleAmount) {
      this.saleAmount = saleAmount;
   }

   public Date getRecordedDate() {
      return recordedDate;
   }

   public void setRecordedDate(Date recordedDate) {
      this.recordedDate = recordedDate;
   }
   
   public abstract Class<E> getThisClass();
   
   @SuppressWarnings({ "unchecked"})
   public E getPrevious() {
           String query = "FROM " + getThisClass().getSimpleName() +  " ORDER BY recordedDate DESC";
      List<E> rtn = DB.getDB().createSession().createQuery(query).setMaxResults(1).list();
      return rtn.size() > 0 ? rtn.get(0) : null;
   }
   
}
