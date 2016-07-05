import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestResMpiTxn
{
  public static void main(String args[]) throws IOException
  {

/********************** Request Variables ****************************/

        String host = "esqa.moneris.com";
        String store_id = "moneris";
        String api_token = "hurgle";

/********************** Transaction Variables ************************/

        String data_key = "FnUlvWe56jgVPvG4xaW2ynRwa";
        String amount = "1.00";
        String xid = "12345678910111215001";
        String MD = xid+"mycardinfo"+amount;
        String merchantUrl = "www.mystoreurl.com";
        String accept = "true";
        String userAgent = "Mozilla";
        
/************************ Request Object ******************************/

	    ResMpiTxn resMpiTxn = new ResMpiTxn(data_key, xid, amount, MD, merchantUrl, accept, userAgent);
        
	    ResolverHttpsPostRequest mpgReq =
            new ResolverHttpsPostRequest(host, store_id, api_token, resMpiTxn);

/************************ Receipt Object ******************************/
	    
	    try
        {
            ResolverReceipt resreceipt = mpgReq.getResolverReceipt();
            
            System.out.println("MPISuccess = " + resreceipt.getMpiSuccess());
            
            if ( resreceipt.getMpiSuccess().equals("true") )
			{
				System.out.println(resreceipt.getMpiInLineForm());
			}
			else
			{
				System.out.println("MPIMessage = " + resreceipt.getMpiMessage());
			}
    		
        }
	    catch (Exception e)
        {
            e.printStackTrace();
        }
  	}  
} // end TestResMpiTxn



