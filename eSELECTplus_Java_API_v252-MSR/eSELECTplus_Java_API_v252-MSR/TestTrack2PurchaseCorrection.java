import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestTrack2PurchaseCorrection
{
  public static void main(String args[]) throws IOException
  {

/********************** Request Variables ****************************/

        String host = "esqa.moneris.com";
        String store_id = "store5";
        String api_token = "yesguy";

/********************** Transaction Variables ************************/

        String order_id;			// will prompt for user inputs
        String txn_number;

		InputStreamReader isr = new InputStreamReader( System.in );
		BufferedReader stdin = new BufferedReader( isr );

		System.out.print( "Please enter an order ID " );
		order_id = stdin.readLine();

		System.out.println( "Please enter an txn number " );
		txn_number = stdin.readLine();

/*********************** Transaction Object *******************************/

        Track2PurchaseCorrection swipedPurchase
        		= new Track2PurchaseCorrection (order_id, txn_number);

/************************ Request Object ************************************/

        Track2HttpsPostRequest mpgReq =
            new Track2HttpsPostRequest(host, store_id, api_token, swipedPurchase);

/************************ Receipt Object ******************************/

        try
        {
            Receipt receipt = mpgReq.getReceipt();

            System.out.println("CardType = " + receipt.getCardType());
            System.out.println("TransAmount = " + receipt.getTransAmount());
            System.out.println("TxnNumber = " + receipt.getTxnNumber());
            System.out.println("ReceiptId = " + receipt.getReceiptId());
            System.out.println("TransType = " + receipt.getTransType());
            System.out.println("ReferenceNum = " + receipt.getReferenceNum());
            System.out.println("ResponseCode = " + receipt.getResponseCode());
            System.out.println("BankTotals = " + receipt.getBankTotals());
            System.out.println("Message = " + receipt.getMessage());
            System.out.println("AuthCode = " + receipt.getAuthCode());
            System.out.println("Complete = " + receipt.getComplete());
            System.out.println("TransDate = " + receipt.getTransDate());
            System.out.println("TransTime = " + receipt.getTransTime());
            System.out.println("Ticket = " + receipt.getTicket());
            System.out.println("TimedOut = " + receipt.getTimedOut());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

  }

}



