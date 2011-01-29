package eu.gaetan.grigis.mail.server;

import java.util.ArrayList;

import eu.gaetan.grigis.mail.client.AlreadyExistException;
import eu.gaetan.grigis.mail.client.MailService;
import eu.gaetan.grigis.mail.client.Mail;
import eu.gaetan.grigis.mail.client.User;
import eu.gaetan.grigis.mail.server.Users;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class MailServiceImpl extends RemoteServiceServlet implements MailService {

	@Override
	public ArrayList<Mail> getMails(String name) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createUser(String name) throws AlreadyExistException {
		User u = new User(name);
		Users.save(u);
	}
}
