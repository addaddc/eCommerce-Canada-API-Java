import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;
//import java.util.Properties;


public class TestPurchase
{
  public static void main(String args[])
  {

        java.util.Date createDate = new java.util.Date();


        String host = "esqa.moneris.com";
        String store_id = "store5";
        String api_token = "yesguy";
        String order_id = "order_"+createDate.getTime();
        String cust_id = "customer1";
        String amount = "1.00";
        String pan = "5454545454545454";
        String expdate = "1111";
        String crypt = "7";

        AvsInfo avs = new AvsInfo ("123", "Edgar Street", "M1M1M1");
        CvdInfo cvd = new CvdInfo ("1", "099");

        Purchase p = new Purchase (order_id, amount, pan, expdate, crypt);

        p.setAvsInfo (avs);
        p.setCvdInfo (cvd);

        //Dynamic Descriptor
        p.setDynamicDescriptor("12345");

        //Using status check constructor
        HttpsPostRequest mpgReq =
            new HttpsPostRequest(host, store_id, api_token, p, false);


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
            System.out.println("StatusCode = " + receipt.getStatusCode());
            System.out.println("StatusMessage = " + receipt.getStatusMessage());
            System.out.println("IsVisaDebit = " + receipt.getIsVisaDebit());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
  }

} // end TestDrive Item
