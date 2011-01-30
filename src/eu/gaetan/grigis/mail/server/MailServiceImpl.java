package eu.gaetan.grigis.mail.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import eu.gaetan.grigis.mail.client.MailService;
import eu.gaetan.grigis.mail.client.Mail;
import eu.gaetan.grigis.mail.client.User;
import eu.gaetan.grigis.mail.client.lib.Cleanup;
import eu.gaetan.grigis.mail.server.Users;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class MailServiceImpl extends RemoteServiceServlet implements MailService {

	@Override
	public ArrayList<Mail> getMails(String name){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Mail> results=null;
		ArrayList<Mail> ret=null;
    	try{
	    	Query query = pm.newQuery(Mail.class);
	    	query.setFilter("to==login");
	        query.declareParameters("String login");
			results = (List<Mail>) query.execute(name);
			if(results!=null)
	    	{
	    		ret=new ArrayList<Mail>();
	    		for (Mail mail : results) {//we must parse data (StreamingResult from query not a list ...)
					ret.add(mail);
				}
	    	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		pm.close();
    	}
    	
		return ret;
	}

	@Override
	public void createUser(String name) {
		User u = new User(name.replaceAll(Cleanup.MAIL_ADRESS_CLEANUP, ""));
		Users.save(u);
	}
}
