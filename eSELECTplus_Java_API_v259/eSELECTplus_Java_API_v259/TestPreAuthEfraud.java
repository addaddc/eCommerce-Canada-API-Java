import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestPreAuthEfraud
{
  public static void main(String args[])
  {

        String host = "esqa.moneris.com";
        String store_id = "store5";
        String api_token = "yesguy";
        String order_id = "Need_Unique_Order_ID_99xccdrtxxx";
        String amount = "10.00";
        String pan = "4242424242424242";
        String expdate = "1111";
        String crypt = "7";

        CvdInfo cvd = new CvdInfo ("1", "099");

        AvsInfo avs = new AvsInfo();

        avs.setAvsStreetNumber("18850");
        avs.setAvsStreetName("56 ST APT301");
        avs.setAvsZipcode("85054");


        PreAuth preAuth = new PreAuth (order_id, amount, pan, expdate, crypt);

        preAuth.setAvsInfo (avs);
        preAuth.setCvdInfo (cvd);

        HttpsPostRequest mpgReq =
            new HttpsPostRequest(host, store_id, api_token, preAuth);

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
            System.out.println("MessageId = " + receipt.getMessageId());
    		System.out.println("AVS Response = " + receipt.getAvsResultCode());
    		System.out.println("CVD Response = " + receipt.getCvdResultCode());
    		System.out.println("ITD Response = " + receipt.getITDResponse());
    		System.out.println("IsVisaDebit = " + receipt.getIsVisaDebit());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

  }

} // end TestPreAuth



