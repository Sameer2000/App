package com.housee.app.mail;


import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.housee.app.model.Forgetpassword;


public class Testmail {
	 private static Transport transport;
	
     public boolean sendMail(Forgetpassword forgetpassword, String url) {
	 try{
		 Logger logger = LoggerFactory.getLogger(Testmail.class);
		 logger.error(forgetpassword.getEmail().toString());
		 logger.error("Searching for dispatcher.................................");
	 //ApplicationContext context = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml"); 
		 //ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com/housee/app/mail/mail-applicationContext.xml");
		// new ClassPathXmlApplicationContext("applicationContext.xml");
	 
	 
	 final String to=forgetpassword.getEmail().toString();/*forgetpassword.toString();*/  //"smrgrover2@gmail.com";//"sks2508@gmail.com";//change accordingly
	 logger.error("Email set..............!!!!!!!!");
	 //Get the session object
	 Properties props = new Properties();
	 props.put("mail.smtp.host", "smtp.gmail.com");
	 props.put("mail.smtp.socketFactory.port", "465");
	 props.put("mail.smtp.socketFactory.class",
         "javax.net.ssl.SSLSocketFactory");
	 props.put("mail.smtp.auth", "true");
	 props.put("mail.smtp.port", "465");
	 logger.debug(props.getProperty("mail.smtp.host", "mail.smtp.socketFactory"));
	 Session session = Session.getDefaultInstance(props,
     new javax.mail.Authenticator() {
     protected PasswordAuthentication getPasswordAuthentication() {
     return new PasswordAuthentication("smrgrover2@gmail.com","Sameer_grover2000");//change accordingly
   }
  });
 
//compose message
   MimeMessage message = new MimeMessage(session);
   message.setFrom(new InternetAddress("smrgrover2@gmail.com"));//change accordingly.
   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
   message.setSubject("Housee | Forget Password");
   message.setText("Testing.......Link");
   message.setText("Forget Password Link");
   message.setText(url);
   
   //send message
   transport.send(message);
   System.out.println("message sent successfully");
   return true; 
  }
    catch (MessagingException e) {e.printStackTrace();}
    return false;
 }
 
     public boolean sendMail(Forgetpassword forgetpassword,String winner,String loses,String pendingamount) {
    	 try{
    		 Logger logger = LoggerFactory.getLogger(Testmail.class);
    		 logger.error(forgetpassword.getEmail().toString());
    		 logger.error("Searching for dispatcher.................................");
    	 //ApplicationContext context = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml"); 
    		 //ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com/housee/app/mail/mail-applicationContext.xml");
    		// new ClassPathXmlApplicationContext("applicationContext.xml");
    	 
    	 
    	 final String to=forgetpassword.getEmail().toString();/*forgetpassword.toString();*/  //"smrgrover2@gmail.com";//"sks2508@gmail.com";//change accordingly
    	 logger.error("Email set..............!!!!!!!!");
    	 //Get the session object
    	 Properties props = new Properties();
    	 props.put("mail.smtp.host", "smtp.gmail.com");
    	 props.put("mail.smtp.socketFactory.port", "465");
    	 props.put("mail.smtp.socketFactory.class",
             "javax.net.ssl.SSLSocketFactory");
    	 props.put("mail.smtp.auth", "true");
    	 props.put("mail.smtp.port", "465");
    	 logger.debug(props.getProperty("mail.smtp.host", "mail.smtp.socketFactory"));
    	 Session session = Session.getDefaultInstance(props,
         new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
         return new PasswordAuthentication("smrgrover2@gmail.com","Sameer_grover2000");//change accordingly
       }
      });
     
    //compose message
       MimeMessage message = new MimeMessage(session);
       message.setFrom(new InternetAddress("smrgrover2@gmail.com"));//change accordingly.
       message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
       message.setSubject("Housee | Forget Password");
       message.setText("Testing.......Link");
       
       //send message
       transport.send(message);
       System.out.println("message sent successfully");
       return true; 
      }
        catch (MessagingException e) {e.printStackTrace();}
        return false;
     }
     
}