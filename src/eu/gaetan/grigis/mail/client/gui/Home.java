package eu.gaetan.grigis.mail.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import eu.gaetan.grigis.mail.client.lib.Config;


public class Home extends Composite {
	interface Binder extends UiBinder<Widget, Home> { }
	private static final Binder binder = GWT.create(Binder.class);
	Mail p;
	@UiField Button createAdress;
	@UiField TextBox mailAdress;
	@UiField Anchor mailDomain;
	public Home(Mail parent)
	{
		p=parent;
	}
	
	@UiHandler("createAdress")
	void onCreateAdressClicked(ClickEvent event) {
	    p.createUser(mailAdress.getText().replaceAll(Config.MAIL_ADRESS_CLEANUP, ""), true);
	}
	
	public void setMailDomain()
	{
		mailDomain.setText(Config.MAIL_DOMAIN);
	}
}
