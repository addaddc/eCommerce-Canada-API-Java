import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestResTokenizeCC
{
  public static void main(String args[]) throws IOException
  {

/********************** Request Variables ****************************/

        String host = "esqa.moneris.com";
        String store_id = "moneris";
        String api_token = "hurgle";

        String order_id = "order_1331744329336";
        String txn_number = "144195-0_7";
        
        String phone = "55555555555";
        String email = "test.user@moneris.com";
        String note = "my note";
        String cust_id = "customer2";
        
        AvsInfo avsCheck = new AvsInfo();
        
        avsCheck.setAvsStreetNumber("213");
        avsCheck.setAvsStreetName("Payton Street");
        avsCheck.setAvsZipcode("M1M1M1");
                
		ResTokenizeCC resTokenizeCC = new ResTokenizeCC (order_id, txn_number);

		resTokenizeCC.setCustId(cust_id);
		resTokenizeCC.setPhone(phone);
		resTokenizeCC.setEmail(email);
		resTokenizeCC.setNote(note);
		resTokenizeCC.setAvsInfo(avsCheck);
				
		//resTokenizeCC.setDataKeyFormat("1"); //1=F6L4 w/ Length preserve, 2=F6L4 w/o Length preserve
		
		ResolverHttpsPostRequest mpgReq =
            new ResolverHttpsPostRequest(host, store_id, api_token, resTokenizeCC);

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
} // end TestResAddCC example
