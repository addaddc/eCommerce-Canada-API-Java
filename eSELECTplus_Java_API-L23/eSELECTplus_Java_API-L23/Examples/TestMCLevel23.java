/**
 *@author Patrick Diab
 *@Created Jun. 14, 2004
 */

import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestMCLevel23
{
  public static void main(String args[]) throws IOException
  {

	  String host = "esqa.moneris.com";
	  String store_id = "moneris";
	  String api_token = "hurgle";
	  String order_id;
	  String txn_number;
	  String customerCode="ACCOUNTING OPERAT";
	  String taxAmount0="0.98";
	  String freightAmount="0.0";
	  String shipToPosCode="V2V8";
	  String shipFromPosCode=" 3W9";
	  String dutyAmount="0.0";
	  String altTaxAmtInd="Y";
	  String altTaxAmt="0.0";
	  String desCouCode="CAN";
	  String supData="MASTERCARD";
	  String salTaxColInd="Y";

	  //Read in order_id and txn_number
	  InputStreamReader isr = new InputStreamReader( System.in );
	  BufferedReader stdin = new BufferedReader( isr );

	  System.out.print( "Please enter an order ID: " );
	  order_id = stdin.readLine();

	  System.out.print( "Please enter a txn number: " );
	  txn_number = stdin.readLine();

	  //Creating a MCLevel2 Transaction
	  MCLevel2Transaction level2=new MCLevel2Transaction(customerCode, taxAmount0,freightAmount, shipToPosCode, shipFromPosCode, dutyAmount, altTaxAmtInd,
		  altTaxAmt, desCouCode, supData,salTaxColInd);
	  //Creating a MCkLevel3 Transaction
	  MCLevel3Transaction[] level3=new MCLevel3Transaction[2];

	  String productCode="OPTIBELT 4L1";
	  String itemDescription="OPTIBELT 4L250 FHP BELT";
	  String itemQuantity="2";
	  String itemUom="EA";
	  String extItemAmount="0.86";
	  String discountInd="N";
	  String discountAmount="0.0";
	  String netGroIndForExtItemAmt="N";
	  String taxRateApp="0.0";
	  String taxTypeApp="";
	  String taxAmount="0.0";
	  String debit_credit_int="1";
	  String altTaxIdeAmt="0.0";

	  level3[0]=new MCLevel3Transaction(productCode, itemDescription,itemQuantity,
										itemUom, extItemAmount, discountInd,discountAmount,
										netGroIndForExtItemAmt, taxRateApp, taxTypeApp,taxAmount,
										altTaxIdeAmt);
	  level3[1]=new MCLevel3Transaction(productCode, itemDescription,itemQuantity,
			itemUom, extItemAmount, discountInd,discountAmount,
			netGroIndForExtItemAmt, taxRateApp, taxTypeApp,taxAmount,altTaxIdeAmt);
		try
        {
			L23HttpsPostRequest request=new L23HttpsPostRequest(host, store_id, api_token,
				new MCLevel23(order_id,txn_number, level2, level3));
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

} // end TestMCLevel23


