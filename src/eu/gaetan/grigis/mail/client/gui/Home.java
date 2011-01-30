package eu.gaetan.grigis.mail.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import eu.gaetan.grigis.mail.client.lib.Cleanup;


public class Home extends Composite {
	interface Binder extends UiBinder<Widget, Home> { }
	private static final Binder binder = GWT.create(Binder.class);
	Mail p;
	@UiField Button createAdress;
	@UiField TextBox mailAdress;
	public Home(Mail parent)
	{
		initWidget(binder.createAndBindUi(this));
		p=parent;
	}
	
	@UiHandler("createAdress")
	void onCreateAdressClicked(ClickEvent event) {
	    p.createUser(mailAdress.getText().replaceAll(Cleanup.MAIL_ADRESS_CLEANUP, ""), true);
	}
}
