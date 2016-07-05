import java.io.*; 
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestResAddToken
{
  public static void main(String args[]) throws IOException
  {

/********************** Request Variables ****************************/

        String host = "esqa.moneris.com";
        String store_id = "moneris";
        String api_token = "hurgle";

        String temp_data_key = "ot-UGzU2QehDdbRR6IIQcWkMi17F";        
        String phone = "55555555555";
        String email = "test.user@moneris.com";
        String note = "my note";
        String cust_id = "customer1";
        String crypt_type = "7";
        String expdate = "1212";
                
        AvsInfo avsCheck = new AvsInfo();
        
        avsCheck.setAvsStreetNumber("212");
        avsCheck.setAvsStreetName("Payton Street");
        avsCheck.setAvsZipcode("M1M1M1");
        avsCheck.setAvsEmail("test@email.com");
        
		ResAddToken resAddToken = new ResAddToken (temp_data_key, crypt_type);

		resAddToken.setExpdate(expdate);
		resAddToken.setCustId(cust_id);
		resAddToken.setPhone(phone);
		resAddToken.setEmail(email);
		resAddToken.setNote(note);
		resAddToken.setAvsInfo(avsCheck);
				
		//resAddToken.setDataKeyFormat("1"); //1=F6L4 w/ Length preserve, 2=F6L4 w/o Length preserve
		
		ResolverHttpsPostRequest mpgReq =
            new ResolverHttpsPostRequest(host, store_id, api_token, resAddToken);

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
} // end TestResAddToken example
