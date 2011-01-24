package eu.gaetan.grigis.mail.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import eu.gaetan.grigis.mail.shared.Mail;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("mail")
public interface MailService extends RemoteService {
	ArrayList<Mail> getMails(String name) throws IllegalArgumentException;
	void createUser(String name) throws AlreadyExistException;
}
