/**
 *@author Nick Tolomiczenko and Linley Ali
 *@Created Feb. 08, 2002
 *
 * TestOpenTotals
 *
 *@version
 *@company Moneris/RBCFG
 */

import java.io.*;
import java.util.*;
import java.net.*;
import JavaAPI.*;

public class TestOpenTotals
{
  public static void main(String args[])
  {
		String host = "esqa.moneris.com";
        String store_id = "moneris";
        String api_token = "hurgle";        
		String ecr_no = "66003551";

        HttpsPostRequest mpgReq =
            new HttpsPostRequest(host, store_id, api_token,new OpenTotals (ecr_no));

        try
        {
            Receipt receipt = mpgReq.getReceipt();

            BankTotals bt = receipt.getBankTotals();

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
        catch (Exception e)
        {
            e.printStackTrace();
        }

  }
			
} // end TestOpenTotals



