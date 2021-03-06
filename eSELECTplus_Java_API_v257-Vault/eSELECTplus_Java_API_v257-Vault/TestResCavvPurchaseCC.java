import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestResCavvPurchaseCC
{
  public static void main(String args[]) throws IOException
  {

/********************** Request Variables ****************************/

        String host = "esqa.moneris.com";
        String store_id = "moneris";
        String api_token = "hurgle";
	
/********************** Transaction Variables ************************/

        String data_key = "AyHiyEl5VH9czMiW18tGQK6D0";
        String order_id;  		// will prompt for user inputs
        String cust_id = "Hilton_1";
        String amount = "1.00";
        String dynamic_descriptor = "123456";
        String expdate = "1512";
        String cavv = "AAABBJg0VhI0VniQEjRWAAAAAAA";

		InputStreamReader isr = new InputStreamReader( System.in );
		BufferedReader stdin = new BufferedReader( isr );

		System.out.print( "Please enter an order ID: " );
		order_id = stdin.readLine();

/************************ Request Object ******************************/

	    ResCavvPurchaseCC resCavvPurchaseCC = new ResCavvPurchaseCC(data_key, order_id, amount, cavv);
        
	    resCavvPurchaseCC.setCustId(cust_id);
	    //resPurchaseCC.setExpdate(expdate);
	    resCavvPurchaseCC.setDynamicDescriptor(dynamic_descriptor);
	   	    
	    ResolverHttpsPostRequest mpgReq =
            new ResolverHttpsPostRequest(host, store_id, api_token, resCavvPurchaseCC);

/************************ Receipt Object ******************************/
	    
	    try
        {
            ResolverReceipt resreceipt = mpgReq.getResolverReceipt();
            ResolveData resdata = resreceipt.getResolveData();
 
            System.out.println("DataKey = " + resreceipt.getDataKey());
            System.out.println("ReceiptId = " + resreceipt.getReceiptId());
            System.out.println("ReferenceNum = " + resreceipt.getReferenceNum());
    		System.out.println("ResponseCode = " + resreceipt.getResponseCode());
    		System.out.println("AuthCode = " + resreceipt.getAuthCode());
    		System.out.println("ISO = " + resreceipt.getISO());
    		System.out.println("Message = " + resreceipt.getMessage());
    		System.out.println("TransDate = " + resreceipt.getTransDate());
    		System.out.println("TransTime = " + resreceipt.getTransTime());
    		System.out.println("TransType = " + resreceipt.getTransType());
    		System.out.println("Complete = " + resreceipt.getComplete());
    		System.out.println("TransAmount = " + resreceipt.getTransAmount());
    		System.out.println("CardType = " + resreceipt.getCardType());
    		System.out.println("TxnNumber = " + resreceipt.getTxnNumber());
    		System.out.println("TimedOut = " + resreceipt.getTimedOut());
    		System.out.println("ResSuccess = " + resreceipt.getResSuccess());
    		System.out.println("PaymentType = " + resreceipt.getPaymentType());
    		System.out.println("CavvResultCode = " + resreceipt.getCavvResultCode() + "\n");
  		
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
} // end TestResCavvPurchaseCC



