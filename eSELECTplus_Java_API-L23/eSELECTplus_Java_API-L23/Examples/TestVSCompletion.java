/**
 *@author Patrick Diab
 *@Created Jun. 11, 2004
 */

import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestVSCompletion
{
  public static void main(String args[]) throws IOException
  {

    String host = "esqa.moneris.com";
    String store_id = "moneris";
    String api_token = "hurgle";
    String order_id;
    String amount = "1.00";
    String txn_number;
    String crypt = "7";

	//Read in order_id and txn_number
	InputStreamReader isr = new InputStreamReader( System.in );
	BufferedReader stdin = new BufferedReader( isr );

	System.out.print( "Please enter an order ID: " );
	order_id = stdin.readLine();

	System.out.print( "Please enter a txn number: " );
	txn_number = stdin.readLine();

	  String orderLevelGst = "0.0";
		if ( args.length > 7 )
		{
		  orderLevelGst = args[7];
		}
		String merchantGstNo = "";
		if ( args.length > 8 )
		{
		  merchantGstNo = args[8];
		}
		String orderLevelPst = "0.0";
		if ( args.length > 9 )
		{
		  orderLevelPst = args[9];
		}
		String merchantPstNo = "";
		if ( args.length > 10 )
		{
		  merchantPstNo = args[10];
		}
		String cri = "";
		if ( args.length > 11 )
		{
		  cri = args[11];
		}

		L23HttpsPostRequest request=new L23HttpsPostRequest(host, store_id, api_token,
									new VSCompletion(order_id, amount, txn_number, crypt,
									orderLevelGst,merchantGstNo,cri,orderLevelPst,merchantPstNo));


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

} // end TestVSCompletion



