import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestResIscorporatecard
{
  public static void main(String args[]) throws IOException
  {

/********************** Request Variables ****************************/

        String host = "esqa.moneris.com";
        String store_id = "store5";
        String api_token = "yesguy";
        String data_key = "9A143sx23Y2Sb426J45GXYYM8";
        
        ResIscorporatecard resIscorporatecard = new ResIscorporatecard (data_key);

        ResolverHttpsPostRequest mpgReq =
            new ResolverHttpsPostRequest(host, store_id, api_token, resIscorporatecard);

        try
        {
            ResolverReceipt resreceipt = mpgReq.getResolverReceipt();
           
    		System.out.println("DataKey = " + resreceipt.getDataKey());
    		System.out.println("CorporateCard = " + resreceipt.getCorporateCard());
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
} // end TestResIscorporatecard example
