/**
 *@author Patrick Diab
 *@Created Jun. 14, 2004
 *
 */

import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestVSLevel23
{
  public static void main(String args[])
  {
  	  String host = "esqa.moneris.com";
	  String store_id = "moneris";
	  String api_token = "hurgle";
	  String order_id = "Diab215";
	  String txn_number = "362389-18-1";
	  String debitOrCredit = "credit";
	  String cardNumber = "4715368000062694";
	  String amount = "21.01";
	  //mean debit transaction
	  String invoiceType="380";
//	For all the refund, has to use this value
	 

	  
	  if ( debitOrCredit != null && debitOrCredit.equals("credit") )
	  {
		  invoiceType="381";
	  }

	  //Creating a party array of size 2 
	  Party[] party=new Party[2];
	
	  //Buyer infomation
	  party[0]=new Party("BY",null,"IOV_"+txn_number,new Name("Test of GT",null,null),
	  					new Street("1 Test Street",null,null,null),
						new PostalInfo("Etobicoke",null,"M8X 2W8","Canada"),
						new Contact[]{ new Contact("GT Testor",null,null,null)},
						new Ref []{new Ref(null,null,null)});
	  

	 
	  
	  //Supplier information
	  party[1]=new Party("SU",null,null,new Name("GTA papers",null,null),
	  	  					new Street("1 Front STREET","212",null,null),
							new PostalInfo("Toronto",null,"M1K 2Y7","Canada"), 
							new Contact[]{ new Contact("GT Testor",null,null,null)},
							new Ref []{new Ref(null,null,null)});
	  	 
	  	 
	  //This reference is for VAT Reference Number
	  Ref[] refe=new Ref[1];
	  refe[0]= new Ref("ACD",null,"VAT12345");
	  
	
	  String invoiceDate="2004-05-18";
	  
	  InvoiceHeader ih=new InvoiceHeader(new InvoiceType(invoiceType,null),
	  									 new InvoiceStatus("9",null),
										 new TaxTreatment("NLL",null),new DiscountTreatment("TN",null),
										 new InvoiceTreatment("P",null),"ISSUER"+txn_number,invoiceDate,
										 null, new Currency1("CAD",null),party,null,null,null,refe,null,null);
	 
	 BaseItemDetail[] bid=new BaseItemDetail[5];
	 InvoiceDetails[] id=new InvoiceDetails[5];

	 //PartNumDetail[stdValue="CC" means Item uses Commodity Code
	  bid[0]=new BaseItemDetail("1",null,new PartNumDetail[]{new PartNumDetail("CC",null,"CC01","CC01 descr")},
	  new Quantity("1",new UnitOfMeasure("EA",null)));
	  id[0]=new InvoiceDetails(bid[0],"1.01",null,"1.01",null,null,null,null,
		  null,null);
	  //PartNumDetail[stdValue="VP" means Item uses Vendor's Part No
	 
	  bid[1]=new BaseItemDetail("2",null,new PartNumDetail[]{new PartNumDetail("VP",null,"VP02","VP02 descr")},
	  new Quantity("2",new UnitOfMeasure("EA",null)));
	  id[1]=new InvoiceDetails(bid[1],"2.35",null,"4.70",null,null,null,null,null,null);
	  //PartNumDetail[stdValue="VP"]/PartNum='Freight/Shipping' means this line item is for freight
	  
	  bid[2]=new BaseItemDetail("3",null,new PartNumDetail[]{new PartNumDetail("VP",null,"Freight/Shipping","Freight/Shipping")},
	  		 new Quantity("1",new UnitOfMeasure("EA",null)));
	
	  Tax taxFS=new Tax(new TaxFunction("7",null),new TaxType("GST",null),
		  new TaxCategory("S",null),"7.00","1.69","0.21",null);
	  id[2]=new InvoiceDetails(bid[2],"1.69",null,"1.69",new Tax[] {taxFS},null,null,null,
		  null,null);
	  bid[3]=new BaseItemDetail("4",null,new PartNumDetail[]
	  {
		  new PartNumDetail("VP",null,"VP04",
		  "VP04 descr")},
		  new Quantity("4",new UnitOfMeasure("CMT",null)));
	  //This line has 7.00% percent tax
	 
	  Tax tax=new Tax(new TaxFunction("7",null),new TaxType("GST",null),
		  new TaxCategory("S",null),"7.00","13.36","0.94",null);
	  id[3]=new InvoiceDetails(bid[3],"3.34",null,"13.36",new Tax[] {tax},null,null,null,
		  null,null);
	  bid[4]=new BaseItemDetail("5",null,new PartNumDetail[]
	  {
		  new PartNumDetail("VP",null,"Discount",
		  "Discount")},
		  new Quantity("1",new UnitOfMeasure("EA",null)));
	  LineDiscountInfo ldi=new LineDiscountInfo("0.23",null,null);
	  //This line has a 0.23 discount
	  id[4]=new InvoiceDetails(bid[4],"0.23",null,"0.0",null,ldi,null,null,null,null);

	  //There is duty for this order, so TaxFunction has to be "5" as its' stdValue. TaxPercent is 0%,
	  //TaxableAmount is 19.53 and duty amount is 1.00
	  Tax taxDuty=new Tax(new TaxFunction("5",null),new TaxType(null,null),
		  new TaxCategory("S",null),"0.00","19.53","1.00",null);
	  TaxSummary ts1=new TaxSummary(null,taxDuty);
	  // netValue is "19.53", taxValue is "2.06"(All taxes including duty.), gross amount is "21.59"
	
	  InvoiceTotals it=new InvoiceTotals(null,"19.53","2.06",amount);
	
	  ActualPayment ap=new ActualPayment(new PaymentAmount(amount,null),new PaymentMean("ZZZ",null,null),
		  //credit card number for this transaction has to to here
		  invoiceDate,new CardInfo(cardNumber,null,"9999",null,null,null,null),null);

	  InvoiceSummary ins=new InvoiceSummary(new TaxSummary[] {ts1},it,new ActualPayment[] {ap});
	
	  Invoice invoice=new Invoice("1",ih,id,ins);
	 
	  Invoice[] invoiceArray=new Invoice[1];

	  invoiceArray[0]= invoice;
	
	  VSLevel23 visaL23 = new VSLevel23(order_id,txn_number, invoiceArray);
	 
        try
        {	
        	visaL23.toString();
			L23HttpsPostRequest request=new L23HttpsPostRequest(host, store_id, api_token,visaL23);
					
			Receipt receipt = request.getReceipt();

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
