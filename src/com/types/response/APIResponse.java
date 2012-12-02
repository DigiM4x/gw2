package com.types.response;



public class APIResponse {
   protected int count;
   protected Item[] results;
   
   private Integer page;
   private Integer last_page;
   private Integer total;
   
   
   public Integer getTotal() {
      return total;
   }
   public void setTotal(Integer total) {
      this.total = total;
   }
   public Integer getPage() {
      return page;
   }
   public void setPage(Integer page) {
      this.page = page;
   }
   public Integer getLast_page() {
      return last_page;
   }
   public void setLast_page(Integer last_page) {
      this.last_page = last_page;
   }
   public int getCount() {
      return count;
   }
   public void setCount(int count) {
      this.count = count;
   }
   public Item[] getResults() {
      return results;
   }
   public void setResults(Item[] results) {
      this.results = results;
   }
  
}
