import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestResPurchaseCCRecur
{
  public static void main(String args[]) throws IOException
  {

/********************** Request Variables ****************************/

        String host = "esqa.moneris.com";
        String store_id = "store5";
        String api_token = "yesguy";

/********************** Transaction Variables ************************/

        String data_key = "317XmK6171DFgS42d2L8pM8D2";
        String order_id;  		// will prompt for user inputs
        String cust_id = "Hilton_1";
        String amount = "1.00";
        String crypt_type = "7";

		InputStreamReader isr = new InputStreamReader( System.in );
		BufferedReader stdin = new BufferedReader( isr );

		System.out.print( "Please enter an order ID: " );
		order_id = stdin.readLine();

/*********************** Recur Varables ******************************/

		String recur_unit = "month";
		String start_now = "true";
		String start_date = "2010/08/10";
		String num_recurs = "12";
		String period = "1";
		String recur_amount = "1.00";
		
/************************** Recur Object ******************************/

		Recur monthlyPayment = new Recur (recur_unit, start_now, start_date,
										  num_recurs, period, recur_amount);
		
/*********************** Transaction Object *******************************/

        ResPurchaseCC resPurchaseCC = new ResPurchaseCC (data_key, order_id, amount, crypt_type);

	    resPurchaseCC.setCustId(cust_id);
	    resPurchaseCC.setRecur(monthlyPayment);
	    
	    ResolverHttpsPostRequest mpgReq =
            new ResolverHttpsPostRequest(host, store_id, api_token, resPurchaseCC);

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
    		System.out.println("Message = " + resreceipt.getMessage());
    		System.out.println("TransDate = " + resreceipt.getTransDate());
    		System.out.println("TransTime = " + resreceipt.getTransTime());
    		System.out.println("TransType = " + resreceipt.getTransType());
    		System.out.println("Complete = " + resreceipt.getComplete());
    		System.out.println("TransAmount = " + resreceipt.getTransAmount());
    		System.out.println("CardType = " + resreceipt.getCardType());
    		System.out.println("TxnNumber = " + resreceipt.getTxnNumber());
    		System.out.println("TimedOut = " + resreceipt.getTimedOut());
    		System.out.println("RecurSuccess = " + resreceipt.getRecurSuccess());
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
} // end TestResPurchaseCCRecur



