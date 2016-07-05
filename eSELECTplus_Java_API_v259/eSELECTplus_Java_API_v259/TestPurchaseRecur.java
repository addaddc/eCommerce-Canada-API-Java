import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestPurchaseRecur
{
  public static void main(String args[])
  {
		String host = "esqa.moneris.com";
        String store_id = "store5";
        String api_token = "yesguy";
        String order_id = "Need_Unique_Recur_120";
        String amount = "50.00";
        String pan = "4242424242424242";
        String expdate = "1111";
        String crypt = "7";
		String recur_unit = "eom";
		String start_now = "false";
		String start_date = "2011/07/31";
		String num_recurs = "12";
		String period = "30";
		String recur_amount = "10.00";

        HttpsPostRequest mpgReq =
            new HttpsPostRequest(host, store_id, api_token,
                       new Purchase(order_id, amount, pan, expdate, crypt,
										new Recur(	recur_unit,
													start_now,
													start_date,
													num_recurs,
													period,
													recur_amount)));

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

}
