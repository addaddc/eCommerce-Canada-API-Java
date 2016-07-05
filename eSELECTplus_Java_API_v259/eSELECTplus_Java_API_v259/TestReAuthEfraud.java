import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestReAuthEfraud
{
  public static void main(String args[]) throws IOException
  {

/********************** Request Variables ****************************/

        String host = "esqa.moneris.com";
        String store_id = "store5";
        String api_token = "yesguy";

/********************** Transaction Variables ************************/

        String order_id;			// will prompt for user inputs
        String cust_id = "Hilton_1";
        String amount = "1.00";
        String orig_order_id = "apr18test9";
        String txn_number = "59067-0_10";
        String crypt = "7";

		InputStreamReader isr = new InputStreamReader( System.in );
		BufferedReader stdin = new BufferedReader( isr );

		System.out.print( "Please enter an order ID: " );
		order_id = stdin.readLine();


/********************** Efraud Variables ************************/

		AvsInfo avs = new AvsInfo ("123", "Edgar Street", "M1M1M1");
        CvdInfo cvd = new CvdInfo ("1", "099");



/*********************** Transaction Object *******************************/

        ReAuth r
        		= new ReAuth (order_id, cust_id, amount, orig_order_id, txn_number, crypt);


	    r.setAvsInfo(avs);
	    r.setCvdInfo(cvd);

/************************ Request Object ******************************/

        HttpsPostRequest mpgReq =
            new HttpsPostRequest(host, store_id, api_token, r);



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
            System.out.println("IsVisaDebit = " + receipt.getIsVisaDebit());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

  }

} // end TestReAuth



