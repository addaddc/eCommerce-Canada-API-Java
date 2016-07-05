import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;


public class TestRecurUpdate
{
  public static void main(String args[]) throws IOException
  {

/********************** Request Variables ****************************/

        String host = "esqa.moneris.com";
        String store_id = "store5";
        String api_token = "yesguy";

        String order_id;
        String cust_id = "antonio";
        String recur_amount = "1.00";
        String pan = "4242424242424242";
        String expdate = "1111";
        String add_num = "";
        String total_num = "";
        String hold = "";
        String terminate = "";

		InputStreamReader isr = new InputStreamReader( System.in );
		BufferedReader stdin = new BufferedReader( isr );

		System.out.print( "Please enter an order ID: " );
		order_id = stdin.readLine();

		RecurUpdate recurUpdate = new RecurUpdate (order_id);

		recurUpdate.setCustId(cust_id);
		recurUpdate.setRecurAmount(recur_amount);
		recurUpdate.setPan(pan);
		recurUpdate.setExpDate(expdate);
		recurUpdate.setAddNumRecurs(add_num);
		recurUpdate.setTotalNumRecurs(total_num);
		recurUpdate.setHold(hold);
		recurUpdate.setTerminate(terminate);


        HttpsPostRequest mpgReq =
            new HttpsPostRequest(host, store_id, api_token, recurUpdate);

        try
        {
            Receipt receipt = mpgReq.getReceipt();

    		System.out.println("ReceiptId = " + receipt.getReceiptId());
    		System.out.println("ResponseCode = " + receipt.getResponseCode());
    		System.out.println("Message = " + receipt.getMessage());
    		System.out.println("Complete = " + receipt.getComplete());
    		System.out.println("TransDate = " + receipt.getTransDate());
    		System.out.println("TransTime = " + receipt.getTransTime());
    		System.out.println("TimedOut = " + receipt.getTimedOut());
    		System.out.println("RecurUpdateSuccess = " + receipt.getRecurUpdateSuccess());
    		System.out.println("NextRecurDate = " + receipt.getNextRecurDate());
    		System.out.println("RecurEndDate = " + receipt.getRecurEndDate());

        }
        catch (Exception e)
        {
			e.printStackTrace();
        }
  }

} // end TestRecurUpdate class