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

/**
 * A simple client-side generator of fake email messages.
 */
public class MailItems {
  
  private static ArrayList<MailItem> items = new ArrayList<MailItem>();

  static {
	  //create the fake mail to give some information
      items.add(createFakeMail());
  }

  public static MailItem getMailItem(int index) {
    if (index >= items.size()) {
      return null;
    }
    return items.get(index);
  }

  public static int getMailItemCount() {
    return items.size();
  }

  private static MailItem createFakeMail() {
    return new MailItem(
    		"admin", 
    		"admin@mail-jetable",
    		"[INFO]Howto use this mailing service", 
    		"Dear User,<br><br>You are using a trash mail service "
            + "It's only useful when you have to provide an email adress "
            + "and you don't wan't to give yours, so if you just "
            + "give the mail adress you just created, the mail will be displayed below"
            + "without having to scare about what the one who get this mail will do with it!!!");
  }
  
  /*functions to add mails*/
  public static void addMails(ArrayList<eu.gaetan.grigis.mail.client.Mail> m)
  {
	  MailItem tmp;
	  if(m!=null)
	  {
		  for (eu.gaetan.grigis.mail.client.Mail mail : m) {
			  tmp=new MailItem(mail.getFrom(), mail.getFrom(), mail.getSubject(), mail.getContent());
			  if(!items.contains(tmp))
				  items.add(tmp);
		  }
	  }
  }
}
