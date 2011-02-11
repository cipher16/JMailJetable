package eu.gaetan.grigis.mail.server;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.jdo.PersistenceManager;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session; 
import javax.mail.internet.MimeMessage;

import java.util.Properties; 
import javax.mail.Address;

import eu.gaetan.grigis.mail.client.Mail;
import eu.gaetan.grigis.mail.client.lib.Config;
import eu.gaetan.grigis.mail.server.Users;
import eu.gaetan.grigis.mail.server.PMF;

public class MailHandlerServlet extends HttpServlet { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException { 
		Properties props = new Properties(); 
		Session session = Session.getDefaultInstance(props, null); 
		MimeMessage message;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			message = new MimeMessage(session, req.getInputStream());
			String to=getEmailString(message.getRecipients(Message.RecipientType.TO)).replaceAll(Config.DOMAIN_ADRESS_CLEANUP, "");
			if(Users.isMailRecipientValid(to))
			{
				Mail m=new Mail(
						message.getSubject(),
						getEmailString(message.getFrom()),
						to,
						getHtmlContent(message.getContent())
					);
				pm.makePersistent(m);
			}
			else
				System.out.println("Attempting to send a mail to an unknown or expired recipient : "+to);
		}catch(Exception ex){
			System.out.println("Erreur lors de la sauvegarde!!");
			ex.printStackTrace();
		}finally{pm.close();}
    }
    protected String getEmailString(Address[] a) {
    	String ret="";
    	for (Address address : a) {
			ret+=address.toString()+",";
    	}
    	return ret.substring(0, ret.length()-1);
    }
    protected String getHtmlContent(Object o) throws MessagingException, IOException
    {
    	//TODO: Support several type and file download
    	String ret="";
    	if (o instanceof String) {
    		return (String) o;
    	} else if (o instanceof Multipart) {
    		System.out.println("getHtmlContent : Object is multipart");
    		for (int i=0;i<((Multipart)o).getCount();i++) {
    			String type=((Multipart)o).getBodyPart(i).getContentType();
    			System.out.println("getHtmlContent : "+type);
    			if(type.startsWith("text/"))
    				ret+=getHtmlContent(((Multipart)o).getBodyPart(i).getContent());
			}
    	} else if (o instanceof Message) {
    		System.out.println("getHtmlContent : Object is message");
    	}
    	return ret;
    }
}
