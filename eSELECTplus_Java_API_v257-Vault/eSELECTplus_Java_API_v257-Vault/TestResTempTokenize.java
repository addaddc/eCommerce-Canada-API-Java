import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestResTempTokenize
{
  public static void main(String args[]) throws IOException
  {

/********************** Request Variables ****************************/

        String host = "esqa.moneris.com";
        String store_id = "monca00183";
        String api_token = "BnnzbQ0qhgPQzZJcdkzV";

        String order_id = "order_1340723561129";
        String txn_number = "1983-0_7";
        String crypt_type = "7";
        String duration = "60";
        
		ResTempTokenize resTempTokenize = new ResTempTokenize (order_id, txn_number, duration, crypt_type);
		
		//resTempTokenize.setDataKeyFormat("1"); //1=F6L4 w/ Length preserve, 2=F6L4 w/o Length preserve

		ResolverHttpsPostRequest mpgReq =
            new ResolverHttpsPostRequest(host, store_id, api_token, resTempTokenize);

        try
        {
            ResolverReceipt resreceipt = mpgReq.getResolverReceipt();
            ResolveData resdata = resreceipt.getResolveData();

    		System.out.println("DataKey = " + resreceipt.getDataKey());
    		System.out.println("ResponseCode = " + resreceipt.getResponseCode());
    		System.out.println("Message = " + resreceipt.getMessage());
    		System.out.println("TransDate = " + resreceipt.getTransDate());
    		System.out.println("TransTime = " + resreceipt.getTransTime());
    		System.out.println("Complete = " + resreceipt.getComplete());
    		System.out.println("TimedOut = " + resreceipt.getTimedOut());
    		System.out.println("ResSuccess = " + resreceipt.getResSuccess());
    		System.out.println("PaymentType = " + resreceipt.getPaymentType() + "\n");

 
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
  }
} // end TestResTempTokenize example
