package eu.gaetan.grigis.mail.client.lib;

public class Config {
	public static String MAIL_ADRESS_CLEANUP = "[^-.a-z0-9A-Z]*";//remove illegal character from mail adress string
	public static String DOMAIN_ADRESS_CLEANUP = "@[-.a-z0-9A-Z]+";//remove everything after the @ when storing mail in DB
	public static String NAME_DOMAIN = "mail-jetable.appspot.com";
	public static String MAIL_DOMAIN = "mail-jetable.appspotmail.com";
}
