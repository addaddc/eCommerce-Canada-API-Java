/**
 *@author Patrick Diab
 *@Created Jun. 11, 2004
 */

import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestVSPurchal
{

	public static void main(String args[]) throws IOException
	{

	String host = "esqa.moneris.com";
    String store_id = "moneris";
    String api_token = "hurgle";
	String order_id;
	String txn_number;

	//Read in order_id and txn_number
	InputStreamReader isr = new InputStreamReader( System.in );
	BufferedReader stdin = new BufferedReader( isr );

	System.out.print( "Please enter an order ID: " );
	order_id = stdin.readLine();

	System.out.print( "Please enter a txn number: " );
	txn_number = stdin.readLine();


		VSPurcha data2= new VSPurcha("2.00","M8X 2W8","M1K 2Y7","ssd","frdessd");

		VSPurchl[] data3=new VSPurchl[5];
		data3[0]=new VSPurchl("CC09","DDD","CC01 descr","1","EA","1.01","0","0","0");
		data3[1]=new VSPurchl("CC01","VP02","VP02 descr","2","EA","2.35","0","0","0");
		data3[2]=new VSPurchl("CC01","Freight/Shipping","Freight/Shipping","1","EA","1.69","0.21","7.00","0");
		data3[3]=new VSPurchl("CC01","VP04","VP04 descr","4","CMT","3.34","0.94","7.00","0");
		data3[4]=new VSPurchl("CC01","Discount","Discount","1","EA","0.23","0","0","0.23");
		VSPurchal data=new VSPurchal(order_id,txn_number,data2,data3);



		L23HttpsPostRequest request=new L23HttpsPostRequest(host, store_id, api_token, data);

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

			}//end try
			catch (Exception e)
			{
				e.printStackTrace();
			}//end catch

		}//end main


	}// end TestVSPurchal



