/*
 * Copyright 2007 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package eu.gaetan.grigis.mail.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import eu.gaetan.grigis.mail.client.MailService;
import eu.gaetan.grigis.mail.client.MailServiceAsync;
import eu.gaetan.grigis.mail.client.lib.Cleanup;

/**
 * This application demonstrates how to construct a relatively complex user
 * interface, similar to many common email readers. It has no back-end,
 * populating its components with hard-coded data.
 */
public class Mail implements EntryPoint {

  interface Binder extends UiBinder<DockLayoutPanel, Mail> { }

  interface GlobalResources extends ClientBundle {
    @NotStrict
    @Source("global.css")
    CssResource css();
  }

  private static final Binder binder = GWT.create(Binder.class);

  @UiField TopPanel topPanel;
  @UiField MailList mailList;
  @UiField MailDetail mailDetail;
//  @UiField Shortcuts shortcuts;
  String mailAdress;

  /**
   * This method constructs the application user interface by instantiating
   * controls and hooking up event handler.
   */
  public void onModuleLoad() {
	if(mailAdress==null)
		mailAdress=com.google.gwt.user.client.Window.Location.getParameter("m");
    if(mailAdress==null)
    	displayHomePage();
    else
    {
    	mailAdress=mailAdress.replaceAll(Cleanup.MAIL_ADRESS_CLEANUP, "");
    	//will create user if it doesn't exist when displaying page with parameter m
    	createUser(mailAdress, false);
    	displayWebMail(mailAdress);
    }
  }
  
  public void displayHomePage()
  {
	  	Home.Binder bin=GWT.create(Home.Binder.class);
	  	HTMLPanel outer = (HTMLPanel) bin.createAndBindUi(new Home(this));
		RootLayoutPanel root = RootLayoutPanel.get();
		root.clear();//remove everything before
		root.add(outer);
  }
  
  public void displayWebMail(String mail)
  {
	// Inject global styles.
	    GWT.<GlobalResources>create(GlobalResources.class).css().ensureInjected();
	    // Create the UI defined in Mail.ui.xml.
	    DockLayoutPanel outer = binder.createAndBindUi(this);
	    setMail(mail);
	    // Get rid of scrollbars, and clear out the window's built-in margin,
	    // because we want to take advantage of the entire client area.
	    Window.enableScrolling(false);
	    Window.setMargin("0px");

	    // Special-case stuff to make topPanel overhang a bit.
	    Element topElem = outer.getWidgetContainerElement(topPanel);
	    topElem.getStyle().setZIndex(2);
	    topElem.getStyle().setOverflow(Overflow.VISIBLE);

	    // Listen for item selection, displaying the currently-selected item in
	    // the detail area.
	    mailList.setListener(new MailList.Listener() {
	      public void onItemSelected(MailItem item) {
	        mailDetail.setItem(item);
	      }
	    });

	    // Add the outer panel to the RootLayoutPanel, so that it will be
	    // displayed.
	    RootLayoutPanel root = RootLayoutPanel.get();
	    root.clear();//remove everything before
	    root.add(outer);
	    reloadMails();
  }
  
  protected void setMail(String m)
  {
	  mailAdress=m;
	  topPanel.setMail(m);
	  topPanel.setParent(this);
	  mailDetail.setMail(m);
  }
  
  public void createUser(String name,final boolean changeView)
  {
	  mailAdress = name;
	  final MailServiceAsync mailService = GWT.create(MailService.class);
		mailService.createUser(mailAdress, new AsyncCallback<Void>() {
			@Override public void onSuccess(Void result) {
				if(changeView)
					displayWebMail(mailAdress);
			}
			@Override public void onFailure(Throwable caught) {caught.printStackTrace();}
		});
  }
  private void reloadMails()
  {
	// Schedule the timer to run once in 5 seconds.
	Timer t = new Timer() {
		public void run() {
			final MailServiceAsync mailService = GWT.create(MailService.class);
			mailService.getMails(mailAdress, new AsyncCallback<ArrayList<eu.gaetan.grigis.mail.client.Mail>>() {
				@Override
				public void onSuccess(ArrayList<eu.gaetan.grigis.mail.client.Mail> result) {
					MailItems.addMails(result);
		    		mailList.update();
				}@Override public void onFailure(Throwable caught) {}
			});
			this.schedule(5000);
		}
	};
	t.schedule(5000);
  }
}
