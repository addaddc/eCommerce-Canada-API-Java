import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestReAuthCustInfo
{
  public static void main(String args[]) throws IOException
  {

/********************** Request Variables ****************************/

        String host = "esqa.moneris.com";
        String store_id = "store5";
        String api_token = "yesguy";

/********************** Transaction Variables ************************/

        String order_id;			// will prompt for user inputs
        String cust_id = "Hilton_1";
        String amount = "1.00";
        String orig_order_id = "apr18test9";
        String txn_number = "59067-0_10";
        String crypt = "7";

		InputStreamReader isr = new InputStreamReader( System.in );
		BufferedReader stdin = new BufferedReader( isr );

		System.out.print( "Please enter an order ID: " );
		order_id = stdin.readLine();

/********************* Billing/Shipping Variables ********************/

        String last_name = "Harris";
        String first_name = "Tommie";
        String company_name = "Da Bears";
        String address = "454 Michigan Ave";
        String city = "Chicago";
        String province = "Illinois";
        String zip_code = "99879";
        String country = "USA";
        String phone_number = "764-908-9989";
        String fax = "764-908-9990";
        String tax1 = "1.00";
        String tax2 = "1.00";
        String tax3 = "1.00";
        String shipping_cost = "2.00";

/************************* Line Item Variables *************************/

		String[] name = new String[]{"Mini Bears Helmet", "Mini Bills Helmet"};
		String[] quantity = new String[]{"1", "2"};
		String[] product_code = new String[] {"BEOOOWS9", "BUFD099D"};
		String[] extended_amount = new String[] {"4.00", "6.00"};

/************************ Miscellaneous Variables **********************/

		String email = "T.Harris@ChicagoBears.com";
		String instructions = "Must arrive before opening day at Lambeau";

/*********************** Transaction Object *******************************/

        ReAuth r
        		= new ReAuth (order_id, cust_id, amount, orig_order_id, txn_number, crypt);

/*********************** Billing/Shipping Object **************************/

		BillingLocation billingAddress =
				new BillingLocation  (last_name, first_name, company_name, address,
									   city, province, zip_code, country, phone_number,
									   fax, tax1, tax2, tax3, shipping_cost);

		ShippingLocation shippingAddress =
				new ShippingLocation  (last_name, first_name, company_name, address,
									   city, province, zip_code, country, phone_number,
									   fax, tax1, tax2, tax3, shipping_cost);

/************************ Line Item Object ************************************/

		Item[] lineItems = new Item[]{new Item(name[0], quantity[0], product_code[0], extended_amount[0]),
									  new Item(name[1], quantity[1], product_code[1], extended_amount[1])};


/************************* Customer Information Object *************************/

		CustomerInfo custData =
				new CustomerInfo (billingAddress, shippingAddress, email, instructions, lineItems);


	    r.setCustInfo (custData);

/************************ Request Object ******************************/

        HttpsPostRequest mpgReq =
            new HttpsPostRequest(host, store_id, api_token, r);



/************************ Receipt Object ******************************/

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

} // end TestReAuth



