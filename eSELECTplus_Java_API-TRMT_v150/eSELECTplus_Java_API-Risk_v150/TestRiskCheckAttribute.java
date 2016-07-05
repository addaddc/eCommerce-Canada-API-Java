import java.io.*;
import java.util.*;

import JavaAPIRisk.*;

public class TestRiskCheckAttribute
{
  public static void main(String args[]) throws IOException
  {

/********************** Request Variables ****************************/

        String host = "esqa.moneris.com";
        String store_id = "moneris";
        String api_token = "hurgle";

        String order_id = "riskcheck990";
        String service_type = "session";

        String event_type = "PAYMENT";

        RiskCheck rc = new RiskCheck();

        AttributeAccountInfo aai = new AttributeAccountInfo();

        aai.setDeviceId("");
        aai.setAccountLogin("13195417-8CA0-46cd-960D-14C158E4DBB2");
        aai.setPasswordHash("489c830f10f7c601d30599a0deaf66e64d2aa50a");
        aai.setAccountNumber("3E17A905-AC8A-4c8d-A417-3DADA2A55220");
        aai.setAccountName("4590FCC0-DF4A-44d9-A57B-AF9DE98B84DD");
        aai.setAccountEmail("3CAE72EF-6B69-4a25-93FE-2674735E78E8@test.threatmetrix.com");
        //aai.setCCNumberHash("4242424242424242");
        //aai.setIPAddress("192.168.0.1");
        //aai.setIPForwarded("192.168.1.0");
        aai.setAccountAddressStreet1("3300 Bloor St W");
        aai.setAccountAddressStreet2("4th Flr West Tower");
        aai.setAccountAddressCity("Toronto");
        aai.setAccountAddressState("Ontario");
        aai.setAccountAddressCountry("Canada");
        aai.setAccountAddressZip("M8X2X2");
        aai.setShippingAddressStreet1("3300 Bloor St W");
        aai.setShippingAddressStreet2("4th Flr West Tower");
        aai.setShippingAddressCity("Toronto");
        aai.setShippingAddressState("Ontario");
        aai.setShippingAddressCountry("Canada");
        aai.setShippingAddressZip("M8X2X2");
        aai.setPolicy("");
        aai.setLocalAttrib1("");
        aai.setLocalAttrib2("");
        aai.setLocalAttrib3("");
        aai.setLocalAttrib4("");
        aai.setLocalAttrib5("");
        aai.setLocalAttrib6("");
        aai.setLocalAttrib7("");
        aai.setLocalAttrib8("");
        aai.setTransactionAmount("1.00");
        aai.setTransactionCurrency("USD");
        aai.setPan("4242424242424242");

        //aai.setToken("");

        AttributeQuery aq = new AttributeQuery(order_id, service_type, aai);
        //aq.setPolicyId("");

        aq.setEventType(event_type);

        rc.setAttributeQuery(aq);

        RiskHttpsPostRequest mpgReq =
            new RiskHttpsPostRequest(host, store_id, api_token, rc);

        try
        {
            RiskReceipt receipt = mpgReq.getReceipt();

    		System.out.println("ResponseCode = " + receipt.getResponseCode());
    		System.out.println("Message = " + receipt.getMessage());
    		System.out.println("TxnNumber = " + receipt.getTxnNumber());

    		HashMap results = receipt.getResult();

    		//Iterate through results from ThreatMetrix
    		Iterator iterator = results.keySet().iterator();
    	    while (iterator.hasNext())
    	    {
    	    	String key = (String) iterator.next();
    	    	System.out.println(key + " = " + results.get(key));
    	    }

    	    //Iterate through Rules triggered
    	    Rule[] rules = receipt.getRules();

    	    if (rules!=null)
    	    {
    	    	for(int i=0;i<rules.length;i++)
        	    {
            	    System.out.println("\nRuleName = " + rules[i].getRuleName());
            	    System.out.println("RuleCode = " + rules[i].getRuleCode());
            	    System.out.println("RuleMessageEn = " + rules[i].getRuleMessageEn());
            	    System.out.println("RuleMessageFr = " + rules[i].getRuleMessageFr());
        	    }
    	    }

        }

        catch (Exception e)
        {
			e.printStackTrace();
        }

  }

} // end TestRiskCheckAttribute