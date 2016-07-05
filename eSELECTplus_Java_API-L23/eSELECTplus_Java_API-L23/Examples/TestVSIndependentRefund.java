/**
 *@author Patrick Diab 
 *@Created Jun. 11, 2004
 */

import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestVSIndependentRefund
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
    String order_id = "Diab103";
    String amount = "1.01";
    String pan="4242424242424242";
	String expDate="0606";
    String crypt = "7";
  	
	  String orderLevelGst = "0.0";
		if ( args.length > 8 )
		{
		  orderLevelGst = args[8];
		}
		String merchantGstNo = "";
		if ( args.length > 9 )
		{
		  merchantGstNo = args[9];
		}
		String orderLevelPst = "0.0";
		if ( args.length > 10 )
		{
		  orderLevelPst = args[10];
		}
		String merchantPstNo = "";
		if ( args.length > 11 )
		{
		  merchantPstNo = args[11];
		}
		String cri = "";
		if ( args.length > 12 )
		{
		  cri = args[12];
		}
        
//		<!ELEMENT vsind_refund (order_id, amount, pan, expdate, crypt_type, (order_level_gst, merchant_gst_no)?, (order_level_pst, merchant_pst_no)?, cri?)>
		L23HttpsPostRequest request=new L23HttpsPostRequest(host, store_id, api_token, 
								 new VSIndependentRefund(order_id, amount, pan,expDate, crypt, orderLevelGst, merchantGstNo, 
								 cri, orderLevelPst, merchantPstNo));

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
			
} // end TestMCCompletion



