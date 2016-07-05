import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestResLookupMasked
{
  public static void main(String args[]) throws IOException
  {

/********************** Request Variables ****************************/

        String host = "esqa.moneris.com";
        String store_id = "store5";
        String api_token = "yesguy";
        String data_key = "a8VeEDDf2692T7PDfZ9A4DgFS";
        
        ResLookupMasked resLookupMasked = new ResLookupMasked (data_key);

        ResolverHttpsPostRequest mpgReq =
            new ResolverHttpsPostRequest(host, store_id, api_token, resLookupMasked);

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
  		
    		//Contents of ResolveData
    		System.out.println("Cust ID = " + resdata.getResCustId());
    		System.out.println("Phone = " + resdata.getResPhone());
    		System.out.println("Email = " + resdata.getResEmail());
    		System.out.println("Note = " + resdata.getResNote());
    		System.out.println("MaskedPan = " + resdata.getResMaskedPan());
    		System.out.println("Exp Date = " + resdata.getResExpDate());
    		System.out.println("Crypt Type = " + resdata.getResCryptType());
    		System.out.println("Avs Street Number = " + resdata.getResAvsStreetNumber());
    		System.out.println("Avs Street Name = " + resdata.getResAvsStreetName());
    		System.out.println("Avs Zipcode = " + resdata.getResAvsZipcode());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
  }
} // end TestResLookupMasked example
