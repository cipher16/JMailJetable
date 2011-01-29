package eu.gaetan.grigis.mail.server;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import eu.gaetan.grigis.mail.server.PMF;
import eu.gaetan.grigis.mail.client.User;

public class Users {
	public static boolean isMailRecipientValid(String rec)
    {
    	boolean ret=false;
    	PersistenceManager pm = PMF.get().getPersistenceManager();
    	try{
	    	Query query = pm.newQuery(User.class);
	    	query.setFilter("name==login");
	        query.declareParameters("String login");
			List<User> results = (List<User>) query.execute(rec);
			if(!results.isEmpty())
				ret=true;
    	}catch(Exception e){
    		
    	}finally{
    		pm.close();
    	}
    	return ret;
    }
	
	public static boolean save(User u)
	{
		boolean ret=false;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			if(!isMailRecipientValid(u.getName()))
				pm.makePersistent(u);
		}finally{pm.close();}
		return ret;
	}
}
