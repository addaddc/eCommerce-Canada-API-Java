import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestCompletion
{
  public static void main(String args[])
  {

		String host = "esqa.moneris.com";
        String store_id = "store_ppi";
        String api_token = "ppiguy";
        String order_id = "mar18test5";
        String amount = "0.01";
        String txn_number = "23985-0_9";
        String crypt = "7";
        String dynamic_descriptor = "123456";

        Completion completion = new Completion (order_id, amount, txn_number, crypt);

        completion.setDynamicDescriptor(dynamic_descriptor);

        HttpsPostRequest mpgReq = new HttpsPostRequest(host, store_id, api_token, completion);


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
            System.out.println("ITD Response = " + receipt.getITDResponse());
            System.out.println("IsVisaDebit = " + receipt.getIsVisaDebit());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

  }

} // end TestCompletion



