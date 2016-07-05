import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestResPreAuthCCCustInfo
{
  public static void main(String args[]) throws IOException
  {

/********************** Request Variables ****************************/

        String host = "esqa.moneris.com";
        String store_id = "store5";
        String api_token = "yesguy";

/********************** Transaction Variables ************************/

        String data_key = "317XmK6171DFgS42d2L8pM8D2";
        String order_id;  		// will prompt for user inputs
        String cust_id = "Hilton_1";
        String amount = "1.00";
        String crypt_type = "7";

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

        ResPreauthCC resPreauthCC
        		= new ResPreauthCC (data_key, order_id, cust_id, amount, crypt_type);

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


	    resPreauthCC.setCustInfo (custData);

/************************ Request Object ******************************/

	    ResolverHttpsPostRequest mpgReq =
            new ResolverHttpsPostRequest(host, store_id, api_token, resPreauthCC);

/************************ Receipt Object ******************************/
	    
	    try
        {
            ResolverReceipt resreceipt = mpgReq.getResolverReceipt();
            ResolveData resdata = resreceipt.getResolveData();
 
            System.out.println("DataKey = " + resreceipt.getDataKey());
            System.out.println("ReceiptId = " + resreceipt.getReceiptId());
            System.out.println("ReferenceNum = " + resreceipt.getReferenceNum());
    		System.out.println("ResponseCode = " + resreceipt.getResponseCode());
    		System.out.println("AuthCode = " + resreceipt.getAuthCode());
    		System.out.println("ISO = " + resreceipt.getISO());
    		System.out.println("Message = " + resreceipt.getMessage());
    		System.out.println("TransDate = " + resreceipt.getTransDate());
    		System.out.println("TransTime = " + resreceipt.getTransTime());
    		System.out.println("TransType = " + resreceipt.getTransType());
    		System.out.println("Complete = " + resreceipt.getComplete());
    		System.out.println("TransAmount = " + resreceipt.getTransAmount());
    		System.out.println("CardType = " + resreceipt.getCardType());
    		System.out.println("TxnNumber = " + resreceipt.getTxnNumber());
    		System.out.println("TimedOut = " + resreceipt.getTimedOut());
    		System.out.println("ResSuccess = " + resreceipt.getResSuccess());
    		System.out.println("PaymentType = " + resreceipt.getPaymentType() + "\n");
  		
    		//Contents of ResolveData
    		System.out.println("Cust ID = " + resdata.getResCustId());
    		System.out.println("Phone = " + resdata.getResPhone());
    		System.out.println("Email = " + resdata.getResEmail());
    		System.out.println("Note = " + resdata.getResNote());
    		System.out.println("MaskedPan = " + resdata.getResMaskedPan());
    		System.out.println("Exp Date = " + resdata.getResExpDate());
    		System.out.println("Crypt Type = " + resdata.getResCryptType());
    		System.out.println("Avs Street Number = " + resdata.getResAvsStreetNumber());
    		System.out.println("Avs Street Name = " + resdata.getResAvsStreetName());
    		System.out.println("Avs Zipcode = " + resdata.getResAvsZipcode());
    		
        }
	    catch (Exception e)
        {
            e.printStackTrace();
        }
  	}  
} // end TestResPreAuthCCCustInfo



