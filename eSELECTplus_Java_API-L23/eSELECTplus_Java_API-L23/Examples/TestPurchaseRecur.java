/**
 *@author Nick Tolomiczenko and Linley Ali
 *@Created Feb. 08, 2002
 *
 * TestDrive
 *
 *@version
 *@company Moneris/RBCFG
 */

import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestPurchaseRecur
{
  public static void main(String args[])
  {
		String host = args[0];
        String store_id = args[1];
        String api_token = args[2];
        String order_id = args[3];
        String amount = args[4];
        String card = args[5];
        String exp = args[6];
        String crypt = args[7];
		String recur_unit = args[8];
		String start_now = args[9];
		String start_date = args[10];
		String num_recurs = args[11];
		String period = args[12];

        HttpsPostRequest mpgReq =
            new HttpsPostRequest(host, store_id, api_token,
                       new Purchase(order_id, amount, card, exp, crypt,
										new Recur(	recur_unit,	start_now,start_date,
													num_recurs,period, amount)));

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
    		System.out.println("ISO = " + receipt.getISO());
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
			
} // end TestDrive Item
