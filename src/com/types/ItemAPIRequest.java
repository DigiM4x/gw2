package com.types;

import java.util.Arrays;

public class ItemAPIRequest extends APIRequest{
   public ItemAPIRequest(int itemId){
      super(Arrays.asList("item",String.valueOf(itemId)));
   }
}
