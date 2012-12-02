package com.types;

import java.util.List;
import java.util.Map;

public class APIRequest {
   protected List<String> args;
   protected Map<String,String> params;
   public APIRequest(List<String> vals) {
      this(vals,null);
   }
   public APIRequest(List<String> args, Map<String,String> params){
      this.args = args;
      this.params = params;
   }
   public List<String> getArguements() {
      return args;
   }
   public Map<String,String> getParameters() {
      return params;
   }
}