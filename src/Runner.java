import java.util.Arrays;
import java.util.Date;

import com.core.Utils;
import com.db.types.BuyOrder;
import com.gui.CoreTable;
import com.gui.CoreTableModel;
import com.gui.MyFrame;
import com.types.APIRequest;
import com.types.ItemAPIRequest;
import com.types.response.APIResponse;

public class Runner {

   /**
    * @param args
    */
   public static void main(String[] args) {

      // and other configuration if you want, timeouts etc
      // then send JSON request
      // RequestObject request = ...; // POJO with getters or public fields
      // // from org.codeahaus.jackson.map
      //
      // and read response
      // ResponseObject response = mapper.readValue(connection.getInputStream(),
      // ResponseObject.class);
      try {

         String toW = Utils.buildUrl(new APIRequest(Arrays.asList("all-items",
               "5")));
         System.out.println(toW);
         Utils.callAPI(new APIRequest(Arrays.asList("all-items",
               "5")),APIResponse.class);
         Utils.callAPI(new ItemAPIRequest(12128));
         // wr.println(toW);
         /*
          * URL url = new URL(toW); URLConnection connection =
          * url.openConnection(); connection.setDoInput(true);
          * connection.addRequestProperty("User-Agent", "Mozilla/5.0");
          * ObjectMapper map = new ObjectMapper(); APIResponse rs =
          * map.readValue(connection.getInputStream(), APIResponse.class);
          * System.out.println(rs.getCount() + "," + rs.getResults());
          */
        // DB.createConnection();
         CoreTableModel[] m = new CoreTableModel[1];
         BuyOrder test = new BuyOrder();
         test.setAmount(1);
         test.setBuyDate(new Date());
         test.setId(1);
         test.setItemId(1);
         test.setItemName("a");
         test.setPricePerItem(11);
         test.setTotal(111);
         m[0] = test;
         CoreTable test2 = new CoreTable(m, BuyOrder.class);
         MyFrame f = new MyFrame();
         f.addTable(test2, "!");
         test2.addRow(test);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }
}
