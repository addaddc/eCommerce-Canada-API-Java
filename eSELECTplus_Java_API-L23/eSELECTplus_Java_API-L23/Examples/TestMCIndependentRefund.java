/**
 *@author Patrick Diab 
 *@Created Jun. 11, 2004
 */

import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestMCIndependentRefund
{

  public static void main(String args[])
  {

		/*String host = args[0];
        String store_id = args[1];
        String api_token = args[2];
        String order_id = args[3];
        String amount = args[4];
		String pan=args[5];
		String expDate=args[6];
		String crypt = args[7];*/
		
	    String host = "esqa.moneris.com";
	    String store_id = "moneris";
	    String api_token = "hurgle";
	    String order_id = "Diab00";
	    String amount = "1.01";
	    String pan="5454545454545454";
		String expDate="0707";
	    String crypt = "7";
		
		

		L23HttpsPostRequest request=new L23HttpsPostRequest(host, store_id, api_token, 
									new MCIndependentRefund(order_id, amount, pan,expDate, crypt));

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
			
} // end TestMCIndependentRefund



