import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestIDebitPurchase
{
  public static void main(String args[])
  {
		String host = "esqa.moneris.com";
        String store_id = "store5";
        String api_token = "yesguy";
        String order_id = "idebittest1";
        String amount = "1.00";
        String idebit_track2 = "3728024906540591206=0609AAAAAAAAAAAAA";


        HttpsPostRequest mpgReq =
            new HttpsPostRequest(host,store_id,api_token,
                       new IDebitPurchase(order_id,amount,idebit_track2));

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

} // end TestPreAuth



