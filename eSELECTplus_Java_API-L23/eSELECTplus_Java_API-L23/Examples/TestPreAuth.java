/**
 *@author Nick Tolomiczenko and Linley Ali
 *@Created Feb. 08, 2002
 *
 * TestPreAuth
 *
 *@version
 *@company Moneris/RBCFG
 */

import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestPreAuth
{
  public static void main(String args[]) throws IOException
  {
		/*String host = args[0];
        String store_id = args[1];
        String api_token = args[2];
        String order_id = args[3];
        String amount = args[4];
        String card = args[5];
        String exp = args[6];
        String crypt = args[7];*/

        String host = "esqa.moneris.com";
        String store_id = "moneris";
        String api_token = "hurgle";
        String order_id; 				//will prompt for user input
        String amount = "100.00";
        String card = "373269005095005";
        String exp = "1111";
        String crypt = "7";

        InputStreamReader isr = new InputStreamReader( System.in );
        BufferedReader stdin = new BufferedReader( isr );
		System.out.print( "Please enter an order ID: " );
		order_id = stdin.readLine();

        HttpsPostRequest mpgReq =
            new HttpsPostRequest(host, store_id, api_token,
                       new PreAuth(order_id, amount, card , exp, crypt));


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
            System.out.println("CorporateCard = " + receipt.getCorporateCard());
            System.out.println("MessageId = " + receipt.getMessageId());


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

  }

} // end TestPreAuth



