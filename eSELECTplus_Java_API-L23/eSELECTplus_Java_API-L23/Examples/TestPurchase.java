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

public class TestPurchase
{
  public static void main(String args[])
  {
		String host = "esqa.moneris.com";
        String store_id = "moneris";
        String api_token = "hurgle";
        String order_id = "monerisandmike1111gg11aa";
        String amount = "1.01";
        String card = "4242424242424242";
        String exp = "0606";
        String crypt = "6";

        HttpsPostRequest mpgReq =
            new HttpsPostRequest(host, store_id, api_token,
                       new Purchase(order_id, amount, card, exp, crypt));

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
