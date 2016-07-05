import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestResIndRefundCC
{
  public static void main(String args[]) throws IOException
  {

/********************** Request Variables ****************************/

        String host = "esqa.moneris.com";
        String store_id = "store5";
        String api_token = "yesguy";

        String data_key = "IHF9a6VlnrerMDsDHhdhTxc0j";
        String order_id = "res_ind_refund_2";
        String amount = "1.00";
        String cust_id = "customer1";
        String crypt_type = "1";
                
        ResIndRefundCC resIndRefundCC = new ResIndRefundCC(data_key, order_id, amount, crypt_type);
        
        resIndRefundCC.setCustId(cust_id);
        
		ResolverHttpsPostRequest mpgReq =
            new ResolverHttpsPostRequest(host, store_id, api_token, resIndRefundCC);

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
    		System.out.println("IsVisaDebit = " + resreceipt.getIsVisaDebit());
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
} // end TestResIndRefundCC example
