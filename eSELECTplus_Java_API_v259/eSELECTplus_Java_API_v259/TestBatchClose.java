import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestBatchClose
{
  public static void main(String args[])
  {
		String host = "esqa.moneris.com";
        String store_id = "store5";
        String api_token = "yesguy";
 		String ecr_no = "66002163";

        HttpsPostRequest mpgReq =
            new HttpsPostRequest(host, store_id, api_token, new BatchClose (ecr_no));
        try
        {
            Receipt receipt = mpgReq.getReceipt();

            BankTotals bt = receipt.getBankTotals();

            if ( receipt.getReceiptId().equals("Global Error Receipt") )
            {

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
            else
            {
	            for ( Iterator ecrs=bt.getEcrs(); ecrs.hasNext(); )
	            {
	                String ecr = (String)(ecrs.next());

	                System.out.println("ECR: " + ecr);
	                for ( Iterator cardTypes=bt.getCardTypes(ecr);
	                        cardTypes.hasNext(); )
	                {
	                    String cardType = (String)(cardTypes.next());

	                    System.out.println("\tCard Type: " + cardType);

	                    System.out.println("\t\tPurchase: Count = "
	                                        + bt.getPurchaseCount(ecr,cardType)
	                                        + " Amount = "
	                                        + bt.getPurchaseAmount(ecr,cardType));

	                    System.out.println("\t\tRefund: Count = "
	                                        + bt.getRefundCount(ecr,cardType)
	                                        + " Amount = "
	                                        + bt.getRefundAmount(ecr,cardType));

	                    System.out.println("\t\tCorrection: Count = "
	                                        + bt.getCorrectionCount(ecr,cardType)
	                                        + " Amount = "
	                                        + bt.getCorrectionAmount(ecr,cardType));

	                }
	            }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

	}

} // end TestBatchClose



