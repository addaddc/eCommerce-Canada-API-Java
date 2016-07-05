import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestCavvPurchase
{
  public static void main(String args[])
  {

        String host = "esqa.moneris.com";
        String store_id = "store5";
        String api_token = "yesguy";
        String order_id = "cavvtest13";
        String amount = "1.00";
        String pan = "4242424242424242";
        String expdate = "1111";
        String cavv = "AAABBJg0VhI0VniQEjRWAAAAAAA=";

        AvsInfo avs = new AvsInfo ("123", "Edgar Street", "M1M1M1");
        CvdInfo cvd = new CvdInfo ("1", "099");

        CavvPurchase securePurchase = new CavvPurchase (order_id, amount, pan, expdate, cavv);

        securePurchase.setAvsInfo (avs);
        securePurchase.setCvdInfo (cvd);
        
        securePurchase.setDynamicDescriptor("12345");

        HttpsPostRequest mpgReq =
            new HttpsPostRequest(host, store_id, api_token, securePurchase);
        
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
    		System.out.println("AVS Response = " + receipt.getAvsResultCode());
    		System.out.println("CVD Response = " + receipt.getCvdResultCode());
    		System.out.println("CavvResultCode = " + receipt.getCavvResultCode());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
  }

} // end TestDrive Item
