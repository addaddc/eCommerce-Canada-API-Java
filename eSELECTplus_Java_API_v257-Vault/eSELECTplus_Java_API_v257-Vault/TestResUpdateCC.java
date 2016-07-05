import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestResUpdateCC
{
  public static void main(String args[]) throws IOException
  {

/********************** Request Variables ****************************/

        String host = "esqa.moneris.com";
        String store_id = "store5";
        String api_token = "yesguy";

        String data_key = "317XmK6171DFgS42d2L8pM8D2";
        String pan = "4242424242424242";
        String expdate = "1111";
        String phone = "55555555555";
        String email = "test.user@moneris.com";
        String note = "my note";
        String cust_id = "customer2";
        String crypt_type = "7";
                
		AvsInfo avsinfo = new AvsInfo();
        
        avsinfo.setAvsStreetNumber("212");
        avsinfo.setAvsStreetName("Smith Street");
        avsinfo.setAvsZipcode("M1M1M1");
		
		ResUpdateCC resUpdateCC = new ResUpdateCC (data_key);

		resUpdateCC.setAvsInfo(avsinfo);
		resUpdateCC.setCustId(cust_id);
		resUpdateCC.setPan(pan);
		resUpdateCC.setExpdate(expdate);
		resUpdateCC.setPhone(phone);
		resUpdateCC.setEmail(email);
		resUpdateCC.setNote(note);
		resUpdateCC.setCryptType(crypt_type);

		ResolverHttpsPostRequest mpgReq =
            new ResolverHttpsPostRequest(host, store_id, api_token, resUpdateCC);

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
} // end TestResUpdateCC example
