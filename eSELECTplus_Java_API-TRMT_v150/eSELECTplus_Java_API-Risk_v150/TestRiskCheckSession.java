import java.io.*;
import java.util.*;

import JavaAPIRisk.*;

public class TestRiskCheckSession
{
  public static void main(String args[]) throws IOException
  {

/********************** Request Variables ****************************/

        String host = "esqa.moneris.com";
        String store_id = "store1";
        String api_token = "yesguy";

        GregorianCalendar cal = new GregorianCalendar();
        Date today = new Date(cal.getTimeInMillis());
        String order_id = "riskcheck18232"+today.getSeconds();
        //String session_id = "M8ALC20656-20121217144206-oM3qPvFz6E_62";
        String session_id = "58001";
        String service_type = "session-policy";
        String proxyHost = "proxy.monad.moneris.com";
        String proxyPort = "8080";

        String event_type = "";

        RiskCheck rc = new RiskCheck();

        SessionQuery sq = new SessionQuery(order_id, session_id, service_type);
        //sq.setEventType(event_type);

        SessionAccountInfo sai = new SessionAccountInfo();
        //sai.setToken("ot-paLFyaAsMx3xYxZkJaIIhpfYI");
        sai.setPolicy("");
        //sai.setDeviceId("86a5396ab15b460eb8e476cb2a678d41");
        sai.setAccountLogin("13195417-8CA0-46cd-960D-14C158E4DBB2");
        sai.setPasswordHash("489c830f10f7c601d30599a0deaf66e64d2aa50a");
        sai.setAccountNumber("3E17A905-AC8A-4c8d-A417-3DADA2A55220");
        sai.setAccountName("4590FCC0-DF4A-44d9-A57B-AF9DE98B84DD");
        sai.setAccountEmail("3CAE72EF-6B69-4a25-93FE-2674735E78E8@test.threatmetrix.com");
        //sai.setAccountTelephone("204-292-2019");
        sai.setPan("3081991486999999955");
        //sai.setAccountAddressStreet1("92 william gibson bay");
        //sai.setAccountAddressStreet2("4th Flr West Tower");
        //sai.setAccountAddressCity("Winnipeg");
        //sai.setAccountAddressState("MB");
        //sai.setAccountAddressCountry("CA");
        //sai.setAccountAddressZip("M8X2X2");
        //sai.setShippingAddressStreet1("92 william gibson bay");
        //sai.setShippingAddressStreet2("4th Flr West Tower");
        //sai.setShippingAddressCity("Winnipeg");
        //sai.setShippingAddressState("MB");
        //sai.setShippingAddressCountry("CA");
        //sai.setShippingAddressZip("M8X2X2");
        //sai.setLocalAttrib1("a");
        //sai.setLocalAttrib2("b");
        //sai.setLocalAttrib3("c");
        //sai.setLocalAttrib4("d");
        //sai.setLocalAttrib5("e");
        //sai.setLocalAttrib6("f");
        //sai.setLocalAttrib7("g");
        //sai.setLocalAttrib8("h");
        //sai.setTransactionAmount("1.00");
        //sai.setTransactionCurrency("USD");
        //set SessionAccountInfo
        sq.setSessionAccountInfo(sai);

        rc.setSessionQuery(sq);

        RiskHttpsPostRequest mpgReq =
            new RiskHttpsPostRequest(host, store_id, api_token, rc, proxyHost, proxyPort);

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

    	    if(rules != null)
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

} // end TestRiskCheckSession