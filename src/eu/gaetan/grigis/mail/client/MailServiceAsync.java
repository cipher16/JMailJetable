package eu.gaetan.grigis.mail.client;

import java.util.ArrayList;
import com.google.gwt.user.client.rpc.AsyncCallback;
import eu.gaetan.grigis.mail.client.Mail;

/**
 * The async counterpart of <code>MailService</code>.
 */
public interface MailServiceAsync {
	void getMails(String input, AsyncCallback<ArrayList<Mail>> callback) throws IllegalArgumentException;
	void createUser(String name, AsyncCallback<Void> callback) throws AlreadyExistException;
}
