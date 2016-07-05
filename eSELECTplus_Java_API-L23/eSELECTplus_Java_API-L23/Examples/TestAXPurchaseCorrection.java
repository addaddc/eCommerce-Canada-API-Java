import java.io.*;
import java.util.*;
import java.net.*;
import java.lang.*;
import JavaAPI.*;
import JavaAPI.xmlable.*;

public class TestAXPurchaseCorrection
{
  public static void main(String args[]) throws IOException
  {


   	 String host = "esqa.moneris.com";
   	 String store_id = "moneris";
   	 String api_token = "hurgle";
   	 String txn_number;		//will prompt for user input
   	 String order_id;    	//will prompt for user input
   	 String crypt_type="7";

     InputStreamReader isr = new InputStreamReader( System.in );
	 BufferedReader stdin = new BufferedReader( isr );

	 System.out.print( "Please enter an order ID: " );
	 order_id = stdin.readLine();

	 System.out.print( "Please enter a txn number: " );
	 txn_number = stdin.readLine();

	 L23HttpsPostRequest request=new L23HttpsPostRequest(host,store_id,api_token,
										new AXPurchaseCorrection(order_id,txn_number,crypt_type));


        try
        {
            Receipt receipt = request.getReceipt();

            System.out.println("CardType = " + receipt.getCardType());
            System.out.println("TransAmount = " + receipt.getTransAmount());
            System.out.println("TxnNumber = " + receipt.getTxnNumber());
            System.out.println("ReceiptId = " + receipt.getReceiptId());
            System.out.println("TransType = " + receipt.getTransType());
            System.out.println("ReferenceNum = " + receipt.getReferenceNum());
            System.out.println("ResponseCode = " + receipt.getResponseCode());
            System.out.println("ISO = " + receipt.getISO());
            System.out.println("BankTotals = " + receipt.getBankTotals());
            System.out.println("Message = " + receipt.getMessage());
            System.out.println("AuthCode = " + receipt.getAuthCode());
            System.out.println("Complete = " + receipt.getComplete());
            System.out.println("TransDate = " + receipt.getTransDate());
            System.out.println("TransTime = " + receipt.getTransTime());
            System.out.println("Ticket = " + receipt.getTicket());
            System.out.println("TimedOut = " + receipt.getTimedOut());
			System.out.println("CorporateCard = " + receipt.getCorporateCard());
			System.out.println("MessageId = " + receipt.getMessageId());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

  }

} // end AXRefund



