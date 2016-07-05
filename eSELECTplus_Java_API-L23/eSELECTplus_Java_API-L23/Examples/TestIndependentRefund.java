/**
 *@author Nick Tolomiczenko and Linley Ali
 *@Created Feb. 08, 2002
 *
 * TestIndependentRefund
 *
 *@version
 *@company Moneris/RBCFG
 */

import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestIndependentRefund
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

		HttpsPostRequest mpgReq =
            new HttpsPostRequest(host, store_id, api_token,
                       new IndependentRefund(order_id, amount, card, exp, crypt));

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
			
} // end TestIndependentRefund



