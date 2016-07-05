import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestRefund
{
  public static void main(String args[])
  {
		String host = "esqa.moneris.com";
        String store_id = "store5";
        String api_token = "yesguy";
        String order_id = "june1test1";
        String amount = "1.00";
        String crypt = "7";
        String txn_number = "629408-0_7";
        String dynamic_descriptor = "123456";

        Refund r = new Refund(order_id, amount, txn_number, crypt);
        
        r.setDynamicDescriptor(dynamic_descriptor);
        
        HttpsPostRequest mpgReq = new HttpsPostRequest(host, store_id, api_token, r);
                       

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
            System.out.println("IsVisaDebit = " + receipt.getIsVisaDebit());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

  }

} // end TestRefund



