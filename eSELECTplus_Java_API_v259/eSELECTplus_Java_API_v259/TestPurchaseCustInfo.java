import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestPurchaseCustInfo
{
  public static void main(String args[])
  {
		String host = "esqa.moneris.com";
        String store_id = "store5";
        String api_token = "yesguy";
        String order_id = "Ord_112dd233_ZYYUUabcde";
        String amount = "20.00";
        String pan = "4242424242424242";
        String expdate = "1111";
        String crypt = "7";

        Item[] items = new Item[2];

        items[0] = new Item(
            "item1's name",         //name
            "5",                    //quantity
            "item1's product code", //product code
            "1.01");                //extended amount

        items[1] = new Item(
            "item2's name",         //name
            "5",                    //quantity
            "item2's product code", //product code
            "2.02");                //extended amount

        BillingLocation billing = new BillingLocation(
                                        "Smith",                //last name
                                        "Bob",                  //first name
                                        "Widget é Company Inc.",  //company name
                                        "111 Bolts Ave.",       //address
                                        "Toronto",              //city
                                        "Ontario",              //province
                                        "M8T 1T8",              //postal code
                                        "Canada",               //country
                                        "416-555-5555",         //phone
                                        "416-555-5555",         //fax
                                        "123.45",               //federal tax
                                        "12.34",                //prov tax
                                        "15.45",                //luxury tax
                                        "456.23");              //shipping cost

        ShippingLocation shipping = new ShippingLocation(
                                        "Smith",                //last name
                                        "Bob",                  //first name
                                        "Widget Company Inc.",  //company name
                                        "111 Bolts Ave.",       //address
                                        "Toronto",              //city
                                        "Ontario",              //province
                                        "M8T 1T8",              //postal code
                                        "Canada",               //country
                                        "416-555-5555",         //phone
                                        "416-555-5555",         //fax
                                        "123.45",               //federal tax
                                        "12.34",                //prov tax
                                        "15.45",                //luxury tax
                                        "456.23");              //shipping cost

        String email = new String("nick@widget.com");

        String instructions = new String("Make it fast!");

        CustomerInfo cust_info =
                new CustomerInfo(billing,shipping,email,instructions,items);

        HttpsPostRequest mpgReq =
            new HttpsPostRequest(host, store_id, api_token,
                       new Purchase(order_id, amount, pan, expdate, crypt,cust_info));

	System.out.println (mpgReq);

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

} // end TestPurchaseL3Data
