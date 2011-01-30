package eu.gaetan.grigis.mail.client.lib;

public class Cleanup {
	public static String MAIL_ADRESS_CLEANUP = "[^-.a-z0-9A-Z]*";//remove illegal character from mail adress string
	public static String DOMAIN_ADRESS_CLEANUP = "@[-.a-z0-9A-Z]+";//remove everything after the @ when storing mail in DB
}
