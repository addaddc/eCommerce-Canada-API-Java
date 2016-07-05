import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;


public class TestPurchaseEfraud
{
  public static void main(String args[]) throws IOException
  {
		String host = "esqa.moneris.com";
	  	String store_id = "store5";
        String api_token = "yesguy";
        String amount = "1.00";

        String card = "4242424242424242";
        String exp = "1111";
        String crypt = "7";
        String order_id = "";

		InputStreamReader isr = new InputStreamReader( System.in );
		BufferedReader stdin = new BufferedReader( isr );

		System.out.print( "Please enter an order ID: " );
		order_id = stdin.readLine();

        CvdInfo cvd = new CvdInfo ("1", "1327");

        Purchase purchase = new Purchase (order_id, amount, card, exp, crypt);

        AvsInfo avs = new AvsInfo();

        avs.setAvsStreetNumber("18850");
        avs.setAvsStreetName("56 ST APT301");
        avs.setAvsZipcode("85054");

        purchase.setAvsInfo (avs);
        purchase.setCvdInfo (cvd);

        HttpsPostRequest mpgReq =
            new HttpsPostRequest(host, store_id, api_token, purchase);

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
    		System.out.println("ITD Response = " + receipt.getITDResponse());
    		System.out.println("IsVisaDebit = " + receipt.getIsVisaDebit());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
  }

} // end TestDrive Item
