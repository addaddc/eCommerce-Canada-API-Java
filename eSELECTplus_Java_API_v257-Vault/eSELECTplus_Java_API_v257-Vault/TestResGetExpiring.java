import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestResGetExpiring
{
  public static void main(String args[]) throws IOException
  {

/********************** Request Variables ****************************/

        String host = "esqa.moneris.com";
        String store_id = "store1";
        String api_token = "yesguy";
                
        ResGetExpiring resGetExpiring = new ResGetExpiring();

        ResolverHttpsPostRequest mpgReq =
            new ResolverHttpsPostRequest(host, store_id, api_token, resGetExpiring);

        try
        {
            ResolverReceipt resreceipt = mpgReq.getResolverReceipt();
 
            System.out.println("DataKey = " + resreceipt.getDataKey());
    		System.out.println("ResponseCode = " + resreceipt.getResponseCode());
    		System.out.println("Message = " + resreceipt.getMessage());
    		System.out.println("TransDate = " + resreceipt.getTransDate());
    		System.out.println("TransTime = " + resreceipt.getTransTime());
    		System.out.println("Complete = " + resreceipt.getComplete());
    		System.out.println("TimedOut = " + resreceipt.getTimedOut());
    		System.out.println("ResSuccess = " + resreceipt.getResSuccess());
    		System.out.println("PaymentType = " + resreceipt.getPaymentType() + "\n");
  		
    		//ResolveData for all expiring profiles
    		for (int i = 0;i<resreceipt.getExpResolveData().size();i++)
            {
    			System.out.println("Data Key = " + resreceipt.getExpDataKey(i));
    			System.out.println("Payment Type = " + resreceipt.getExpPaymentType(i));
    			System.out.println("Cust ID = " + resreceipt.getExpCustId(i));
    			System.out.println("Phone = " + resreceipt.getExpPhone(i));
    			System.out.println("Email = " + resreceipt.getExpEmail(i));
    			System.out.println("Note = " + resreceipt.getExpNote(i));
    			System.out.println("Masked Pan = " + resreceipt.getExpMaskedPan(i));
    			System.out.println("Exp Date = " + resreceipt.getExpExpdate(i));
    			System.out.println("Crypt Type = " + resreceipt.getExpCryptType(i));
    			System.out.println("Avs Street Number = " + resreceipt.getExpAvsStreetNumber(i));
    			System.out.println("Avs Street Name = " + resreceipt.getExpAvsStreetName(i));
    			System.out.println("Avs Zipcode = " + resreceipt.getExpAvsZipcode(i) + "\n");
            }
    		
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
  }
} // end TestResGetExpiring example
