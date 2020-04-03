package com.hairshop.contorller;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {
 public SMTPAuthenticator() {
	 super();
 }
 public PasswordAuthentication getPasswordAuthentication() {
	return null;
	
 }
}
