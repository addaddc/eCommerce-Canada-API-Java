import java.io.*;
import java.util.*;
import java.net.*;
import java.lang.*;
import JavaAPI.*;
import JavaAPI.xmlable.*;

//Author: Zaid Yahya

public class TestAXForcePost
{
  public static void main(String args[]) throws IOException
  {

	/********************* Request Variables ************************/

    String host = "esqa.moneris.com";
    String store_id = "moneris";
    String api_token = "hurgle";

    /******************* Transaction Variables ***********************/

    String amount = "62.37";
    String order_id;		//will prompt for user input
    String pan = "373269005095005";
    String expiry_date = "0812";
    String auth_code;		//will prompt for user input

	//Read in order_id and txn_number
	InputStreamReader isr = new InputStreamReader( System.in );
	BufferedReader stdin = new BufferedReader( isr );

	System.out.print( "Please enter an order ID: " );
	order_id = stdin.readLine();

	System.out.print( "Please enter an authorization number: " );
	auth_code = stdin.readLine();

	/******************** Table 1 - Heading **************************/

	String n101 = "R6";								//Entity ID Code
	String n102 = "Retailing Inc. International";	//Name
	String n301 = "919 Oriole Rd.";					//Address Line 1
	String n401 = "Toronto";						//City
	String n402 = "On";								//State or Province
	String n403 = "HIT6W3";							//Postal Code

	String big04 = "PO00998EU3";					//Purchase Order Number
	String big05 = "RELNUM300E";					//Release Number
	String big10 = "INVNUM002Q";					//Invoice Number

	String[] ref01 = new String[]{"4C", "CR"};		//Reference ID Qualifier
	String[] ref02 = new String[]{"M5E9I8", "H1G6H7"};	//Reference ID

	RefAx [] ref = null;
    try
	{
		ref = new RefAx[2];
	   	ref[0]= new RefAx(ref01[0], ref02[0]);
	   	ref[1]= new RefAx(ref01[1], ref02[1]);
	}
		catch (Exception e)
	{
		System.out.println ("RefAx Constructor error");
	    e.printStackTrace();
	}

	N1_Loop[] n1loop = new N1_Loop[1];
	try
	{
		n1loop[0]= new N1_Loop(n101, n102, n301,
							   "", n401, n402, n403, ref);

	}
	catch (Exception e)
	{
		System.out.println ("N1_Loop Constructor error");
	    e.printStackTrace();
    }

	Table1 tbl1 = null;

	try
	{
		tbl1 = new Table1(big04, big05, big10, n1loop);
	}
	catch (Exception e)
	{
		System.out.println ("Table1 Constructor error");
	    e.printStackTrace();
    }

	/******************** Table 2 - Detail **************************/
	//the sum of the extended amount field (i.e. paramater #7 of It1_Loop
	//must equal the level 1 amount field)

	String[] it102 = new String[]{"1", "1", "1", "1"};  //Line item quantity invoiced
	String[] it103 = new String[]{"EA", "EA", "EA", "EA"};	//Line item unit of basis of measurement code
	String[] it104 = new String[]{"10.00", "25.00", "8.62", "10.00"};  //Line item unit price
	String[] it105 = new String[]{"","","",""};  //Line item basis of unit price code

	String[] it10618 = new String[]{"MG", "MG", "MG", "MG"};	//Product/Service ID qualifier
	String[] it10719 = new String[]{"DJ99F", "UY7UU7", "YYTTD9", "SW3544"}; //Product/Service ID

	String[] txi01_GST = new String[]{"GS", "GS", "GS", "GS"};  //Tax Type Code
	String[] txi02_GST = new String[]{"0.70", "1.75", "1.00", "0.80"};  //Monetary Amount
	String[] txi03_GST = new String[]{"","","",""};		//Percent
	String[] txi06_GST = new String[]{"","","",""};		//Tax Exempt Code

	String[] txi01_PST = new String[]{"PG", "PG", "PG", "PG"};    //Tax Type Code
	String[] txi02_PST = new String[]{"0.80", "2.00", "1.00", "0.80"};  //Monetary Amount
	String[] txi03_PST = new String[]{"", "", "", ""};	//Percent
	String[] txi06_PST = new String[]{"", "", "", ""};	//Tax Exempt Code

	Txi[] taxGST = new Txi[]{new Txi(txi01_GST[0], txi02_GST[0], txi03_GST[0], txi06_GST[0]),
							  new Txi(txi01_GST[1], txi02_GST[1], txi03_GST[1], txi06_GST[1]),
							  new Txi(txi01_GST[2], txi02_GST[2], txi03_GST[2], txi06_GST[2]),
							  new Txi(txi01_GST[3], txi02_GST[3], txi03_GST[3], txi06_GST[3])};

	Txi[] taxPST = new Txi[]{new Txi(txi01_PST[0], txi02_PST[0], txi03_PST[0], txi06_GST[0]),
							  new Txi(txi01_PST[1], txi02_PST[1], txi03_PST[1], txi06_GST[1]),
							  new Txi(txi01_PST[2], txi02_PST[2], txi03_PST[2], txi06_GST[2]),
							  new Txi(txi01_PST[3], txi02_PST[3], txi03_PST[3], txi06_GST[3])};

	String[] pam05 = new String[]{"11.50", "28.75", "10.62", "11.50"};   //Extended Line-Item Amount
	String[] pid05 = new String[]{"Stapler", "Lamp", "Bottled Water", "Fountain Pen"}; //Line-Item Description

	ArrayList itQual = new ArrayList();
	ArrayList itLevel3Taxes = new ArrayList();

	int numOfItems = pid05.length;	//get total number of items

	It1_Loop[] itemLoop = new It1_Loop[numOfItems];

	for (int item = 0; item < numOfItems; item++)	//one iteration for each item
	{
		try
		{
			itQual.add (new It106s[]{new It106s(it10618[item], it10719[item])}); //item information for each item

			itLevel3Taxes.add (new Txi[]{taxGST[item], taxPST[item]}); //taxes for each item

			Object tempItArray[] = itQual.toArray();
			It106s itArray[] = (It106s[])(tempItArray[item]);

			Object tempItLevel3Taxes[] = itLevel3Taxes.toArray();
			Txi txiArray[] = (Txi[])(tempItLevel3Taxes[item]);

			itemLoop[item] = new It1_Loop(it102[item], it103[item], it104[item], it105[item],
						 	 			  itArray, txiArray, pam05[item], pid05[item]);
		}
		catch (Exception e)
		{
			System.out.println ("Table1 Constructor error");
			e.printStackTrace();
		}
	}

	Table2 tbl2 = null;
	try
	{
			tbl2 = new Table2(itemLoop);
	}
	catch (Exception e)
	{
		System.out.println ("Tabl2 Constructor error");
		e.printStackTrace();
	}

	/*********************** Table 3 - Summary ********************************/

	Txi[] taxTbl3 = new Txi[3];
	taxTbl3[0] = new Txi("GS", "4.25", "","");
	taxTbl3[1] = new Txi("PG", "4.60", "","");
	taxTbl3[2] = new Txi("TX", "8.85", "","");

	Table3 tbl3 = new Table3 (taxTbl3);

	AXLevel23 axLevel23 = null;

	try
	{
		 axLevel23 = new AXLevel23(tbl1,tbl2,tbl3);
	}
	catch (Exception e)
	{
		System.out.println ("It106s Constructor error");
		e.printStackTrace();
	}

	/**************************** Request ************************************/

	    L23HttpsPostRequest request=new L23HttpsPostRequest(host,store_id,api_token,
									new AXForcePost(order_id, amount, pan, expiry_date, auth_code, axLevel23));

	/**************************** Receipt ************************************/

        try
        {
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
			System.out.println("CorporateCard = " + receipt.getCorporateCard());
			System.out.println("MessageId = " + receipt.getMessageId());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

  }

} // end TestAXCompletion



