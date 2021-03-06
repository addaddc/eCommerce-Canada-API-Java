import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestResCardVerificationCC
{
  public static void main(String args[]) throws IOException
  {

/********************** Request Variables ****************************/

        String host = "esqa.moneris.com";
        String store_id = "moneris";
        String api_token = "hurgle";

/********************** Transaction Variables ************************/

        String data_key = "zE0lAfC47C01i8BjGVUosXYue";
        String order_id;  		// will prompt for user inputs
        String crypt_type = "7";

		InputStreamReader isr = new InputStreamReader( System.in );
		BufferedReader stdin = new BufferedReader( isr );

		System.out.print( "Please enter an order ID: " );
		order_id = stdin.readLine();

/********************** Efraud Variables ************************/
		AvsInfo avs = new AvsInfo ();
		avs.setAvsStreetName("test ave");
		avs.setAvsStreetNumber("123");
		avs.setAvsZipcode("123456");

        CvdInfo cvd = new CvdInfo ("1", "099");

/*********************** Transaction Object *******************************/

        ResCardVerificationCC resCardVerificationCC = new ResCardVerificationCC (data_key, order_id, crypt_type);

        resCardVerificationCC.setAvsInfo(avs);
        resCardVerificationCC.setCvdInfo(cvd);

        //resCardVerificationCC.setExpdate("1412");

	    ResolverHttpsPostRequest mpgReq =
            new ResolverHttpsPostRequest(host, store_id, api_token, resCardVerificationCC);

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
} // end TestResCardVerificationCC



