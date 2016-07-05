/**
 *@author Nick Tolomiczenko
 *@Created May 15, 2003
 *
 * Example store using Moneris mpiapi-1.00 (MpiAPI.jar) and mpgapi-2.02 (JavaAPI.jar)
 *
 *@version
 *@company Moneris
 */

import java.io.*;
import java.util.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import MpiAPI.*;
import JavaAPI.*;

public class Store extends HttpServlet
{
	static Properties storeProps = null;
	static String esHost = null;
	static String store_id = null;
	static String api_token = null;
	static String storeUrl = null;

	public void init()
	{

		try
		{
	        String servletPathName = getServletContext().getRealPath(getServletName());
	        String webappslocation
	            = servletPathName.substring(0,servletPathName.lastIndexOf('/'))
	                + "/WEB-INF/classes";
	
	        FileInputStream storePropsFile = new FileInputStream(webappslocation
	                                                        + "/store.properties");
	        storeProps = new Properties();
	        storeProps.load(storePropsFile);
	        storePropsFile.close();
	
	        System.setProperty("javax.net.ssl.keyStore",
	            storeProps.getProperty("javax.net.ssl.keyStore"));
	        System.setProperty("javax.net.ssl.keyStorePassword",
	            storeProps.getProperty("javax.net.ssl.keyStorePassword"));
	        System.setProperty("javax.net.ssl.trustStore",
	            storeProps.getProperty("javax.net.ssl.trustStore"));

			esHost = storeProps.getProperty("eSelectPlusHost");
			store_id = storeProps.getProperty("storeId");
			api_token = storeProps.getProperty("apiToken");
			storeUrl = storeProps.getProperty("storeUrl");
		}
		catch(Exception e)
		{
			log(e.getMessage());
		}

	}

    private static String zeroFill(String oldString, int zeroedSize) {

        int oldStringLength = oldString.length();

        StringBuffer sb = new StringBuffer(oldString);

        if( oldStringLength < zeroedSize)
        {
            for(int i=0; i < (zeroedSize-oldStringLength); i++)
            {
                sb.insert(0,"0");
            }

        }
        return sb.toString();
    }

    public void service (HttpServletRequest req, HttpServletResponse res)
                                        throws ServletException, IOException
    {        

		try
		{
		res.setContentType("text/html");

		PrintWriter out = res.getWriter();

		if ( req.getParameter("purchase_amount") != null )
		{
			String agent = req.getHeader("user-agent");
			String accept = req.getHeader("accept");

	        String amount = req.getParameter("purchase_amount");
			String cardNum = req.getParameter("pan");
        	String expdate = req.getParameter("expiry");

			Txn txn = new Txn();
	
			String xid = genXid();
			txn.setXid(xid);
			txn.setAmount(amount);
			txn.setCardNumber(cardNum);
			txn.setExpDate(expdate);
			txn.setMD(genMD(xid,cardNum,expdate,amount));
			txn.setMerchantUrl(storeUrl);
			txn.setHttpAccept(accept);
			txn.setHttpUserAgent(agent);
	
	        HttpsPostMpiRequest mpiReq =
	            new HttpsPostMpiRequest(esHost, store_id, api_token, txn);

			MpiResponse mpiRes = mpiReq.getResponse();

			if ( mpiRes.getSuccess().equals("true") )
			{
				out.print(mpiRes.getInlineForm());
			}
			else
			{
				out.print(mpiRes.getMessage());
			}

		}
		else if ( req.getParameter("PaRes") != null )
		{
			Acs acs = new Acs();

			acs.setPaRes(req.getParameter("PaRes"));
			String MD = req.getParameter("MD");
			acs.setMD(MD);

	        HttpsPostMpiRequest mpiReq =
	            new HttpsPostMpiRequest(esHost, store_id, api_token, acs);

			MpiResponse mpiRes = mpiReq.getResponse();

			if ( mpiRes.getSuccess().equals("true") )
			{
    			out.print("<br>Message = " + mpiRes.getMessage());
    			out.print("<br>cavv = " + mpiRes.getCavv());
    			out.print("<br><br>");

				Map MDMap = getMDMap(MD);

				CavvPurchase cavvPurchase = new CavvPurchase (
													(String)(MDMap.get("xid")),
													(String)(MDMap.get("amount")),
													(String)(MDMap.get("pan")),
													(String)(MDMap.get("expdate")),
													mpiRes.getCavv()
												); 

				HttpsPostRequest mpgReq = new HttpsPostRequest( esHost,
																store_id, api_token,
																cavvPurchase);

		        try
		        {
		            Receipt receipt = mpgReq.getReceipt();
		
		    		out.print("<br>CardType = " + receipt.getCardType());
		    		out.print("<br>TransAmount = " + receipt.getTransAmount());
		    		out.print("<br>TxnNumber = " + receipt.getTxnNumber());
		    		out.print("<br>ReceiptId = " + receipt.getReceiptId());
		    		out.print("<br>TransType = " + receipt.getTransType());
		    		out.print("<br>ReferenceNum = " + receipt.getReferenceNum());
		    		out.print("<br>ResponseCode = " + receipt.getResponseCode());
		    		out.print("<br>ISO = " + receipt.getISO());
		    		out.print("<br>BankTotals = " + receipt.getBankTotals());
		    		out.print("<br>Message = " + receipt.getMessage());
		    		out.print("<br>AuthCode = " + receipt.getAuthCode());
		    		out.print("<br>Complete = " + receipt.getComplete());
		    		out.print("<br>TransDate = " + receipt.getTransDate());
		    		out.print("<br>TransTime = " + receipt.getTransTime());
		    		out.print("<br>Ticket = " + receipt.getTicket());
		    		out.print("<br>TimedOut = " + receipt.getTimedOut());
		
		        }
		        catch (Exception e)
		        {
		            e.printStackTrace(out);
		        }
			}
			else
			{
    			out.print("<br>Message = " + mpiRes.getMessage());
			}
		}
		else
		{
			out.print("<form method=post action='/mpi/servlet/Store'><table><tr><td>Credit Card Number:</td><td colspan><input type=text name=pan size=16 value='4242424242424242'></td></tr><tr><td>Expiry Date:</td><td colspan><input type=text  name=expiry size=4 value='0404'></td></tr><tr><td>Amount:</td><td colspan><input type=text  name=purchase_amount size=4 value='1.01'></td></tr><tr><td colspan=2 align=center><input type=submit  name=submit value='Buy'></td></tr></table></form>");
		}
		}
		catch (Exception e)
		{
		}
	}

	private String genXid()
	{
		return genId(20);
	}

	private String genId(int length)
	{
		String str = null;

		String timeStr = String.valueOf(System.currentTimeMillis());

		int timeStrLen = timeStr.length();

		
		try
		{
			str = timeStr.substring(timeStrLen-length,timeStrLen);
		}
		catch(IndexOutOfBoundsException e)
		{
			str = timeStr;
		}
		
		return zeroFill(str,length);
	}

	private String genMD(String xid, String pan, String expdate, String amount)
	{
		
		return new String(
						"xid=" + xid
					+ "&pan=" + pan
					+ "&expdate=" + expdate
					+ "&amount=" + amount
					);
	}

	private Map getMDMap(String MD)
	{

		String [] keyAndValue = MD.split("&");
		
		Map mdMap = new Hashtable();
	
		for(int i=0; i<keyAndValue.length; i++)
		{
			String []  temp = keyAndValue[i].split("=");
			mdMap.put(temp[0],temp[1]);			

		}

		return mdMap;
	}
	
	


} // end Store
