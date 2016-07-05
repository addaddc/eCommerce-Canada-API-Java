import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;


public class TestForcePost
{
  public static void main(String args[])
  {

/***************************** Request Variables ***************************/

		String host = "esqa.moneris.com";
        String store_id = "store5";
        String api_token = "yesguy";

/*************************** Transactional Variables ***********************/

        String order_id = "Need_Unique_Order_ID";
        String amount = "1.00";
        String pan = "4242424242424242";
        String expdate = "0812";
        String auth_code = "AUTH887";
        String crypt = "7";
        String dynamic_descriptor = "123456";

/***************************** Transaction Object **************************/

		ForcePost fp = new ForcePost (order_id, amount, pan, expdate, auth_code, crypt);
		
		fp.setDynamicDescriptor(dynamic_descriptor);

/******************************** Request Object ***************************/

        HttpsPostRequest mpgReq =
            new HttpsPostRequest(host, store_id, api_token, fp);

/******************************** Response *********************************/

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
