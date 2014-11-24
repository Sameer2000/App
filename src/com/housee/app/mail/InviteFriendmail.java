package com.housee.app.mail;

import java.io.ByteArrayOutputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.housee.app.contants.SystemSettingConstants;
import com.housee.app.test.data.PDFMailFormatData;

public class InviteFriendmail extends SendMailAPI {
	String userFullName,emailAdd,senderName;
	Long roomId = null;
    /**
     * Sends an email with a PDF attachment.
     */
    public void email(String userFullName,Long roomId,String emailAdd,String senderName) {
    	this.userFullName = userFullName;
    	this.roomId = roomId;
    	this.emailAdd = emailAdd;
    	this.senderName = senderName;
    	
    	
        String invitationMail = "<!DOCTYPE html>\n" +
        "<html>\n" +
        "    <head>\n" +
        "        <title></title>\n" +
        "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
        "        <link rel=\"stylesheet\" href=\"email.css\" type=\"text/css\"/>\n" +
        "    </head>\n" +
        "    <body>\n" +
        "        <div class=\"main\" style=\"margin-left: auto;margin-right: auto;background-color: #cccccc;height: 95%;position: relative;padding-top: 2%;padding-bottom: 2%;\">\n" +
        "            <div class=\"inner\" style=\"background-color: whitesmoke;width: 91%;margin-left: auto;margin-right: auto;font-family: arial;padding-bottom: 3%;\">\n" +
        "                <div id=\"look\" style=\"width: 90%;margin-left: auto;margin-right: auto;border-bottom: #666666 1px dotted;\">\n" +
        "                <div class=\"header\" style=\"background-image:url(logo.png);\">\n" +
        "                <img src=\"cid:image1\" width=\"300\" height=\"100\" alt=\"logo\" id=\"pic\"/>\n" +
        "            </div>\n" +
        "            <div class=\"container\">\n" +
/*change this line content*/                "                <span id=\"welcome\" style=\"position: relative;top: 28px;font-weight: bold;font-size: 17px;font-family: arial;\">Hi "+userFullName+",</span>\n" +
        "                <p id=\"text\" style=\"position: relative;top: 25px;text-align: left;width: 570px;font-family: arial;font-size: 14px;\">"+senderName+" sent you an invite for a game of Housee. \n</p><br/>\n" +
        "                <p style=\"font-size: 13px;\">"+
        "                <div class=\"header\" style=\"background-image:url(logo.png);\">\n" +
        "                <img src=\"cid:image2\" width=\"43\" height=\"40\" alt=\"logo\" id=\"pic\"/><a id=\"req\" href=\"#\" style=\"font-weight: bold;color: grey;margin-left: 13px;text-transform: capitalize;\">Join Now</a>" +
        "                </div>\n" +
        "                <p style=\"font-size: 13px;font-weight: bold;\">Game-Details: (Room Id-"+roomId+")</p><br/>\n\n" +
        "                <p style=\"font-size: 13px;\"> This is a System Generated Message.</p>\n" +
        "                <p style=\"font-size: 13px;\">Please do not reply to this e-mail.</p><br/>\n" +
        "                <p style=\"font-size: 13px;\">Thanks<br/>\n" +
        "                Housee Team<br/>\n" +
        "                www.Housee.com</p>\n" +
        "</div>\n" +
        "                </div>\n" +
        "                <div class=\"header look1\" style=\"width: 90%;margin-left: auto;margin-right: auto;\">\n" +
        "                    <p class=\"footext\" style=\"font-size: 10px;\">If you need technical assistance, please contact Housee Help. To unsubscribe or otherwise manage your email preferences, visit Housee.com/in/account. Housee may retire online features 30 days after notice has been posted to www.Housee.com/asia/.</p>\n" +
        "                    <p class=\"footext\" style=\"font-size: 10px;\">PRIVACY POLICY: Our Certified Online Privacy Policy gives you confidence whenever you play Housee Casino. To view our complete Privacy Policy, go to privacy.Housee.com or write to: Privacy Policy Administrator, HOUSEE CASINO., 209 Redwood Shores Parkway, Redwood City, CA 94065.\n" +
        "                    © 2013 Housee App Inc. All Rights Reserved.</p>\n" +
        "                    <p class=\"footext\" style=\"font-size: 10px;\"><a href=\"#\" style=\"font-weight: bold;\">Legal Notice | Privacy Policy | Terms of Service</a></p><br/><br/>\n" +
        "            \n" +
        "            </div>\n" +
        "        </div>\n" +
        "    </body>\n" +
        "</html>\n";
        
        
    	
    	String strSubject = "Game Invitation";
    	
        String recipients[] = {senderName ,SystemSettingConstants.ADMIN_EMAIL_ID}; //replace this with a valid recipient email address
        String subject = strSubject; //this will be the subject of the email
         
         
        try {           
            //construct the text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setContent(invitationMail, "text/html");
            
                         
            //construct the mime multi part
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
             
            //create the sender/recipient addresses
            InternetAddress iaSender = new InternetAddress(senderName);
            int sizeTo=recipients.length;
            InternetAddress[] addressTo = new InternetAddress[sizeTo];
            for (int i = 0; i < sizeTo; i++)
            {
            	addressTo[i] = new InternetAddress(recipients[i]) ;
            }
            //construct the mime message
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setSender(iaSender);
            mimeMessage.setSubject(subject);
            mimeMessage.setRecipients(Message.RecipientType.TO, userFullName);
            mimeMessage.setContent(mimeMultipart);
             
            //send off the email
            Transport.send(mimeMessage);
             
            System.out.println("sent from " + sender + 
                    ", to " + recipients.toString() + 
                    "; server = " + smtpHost + ", port = " + smtpPort);         
        } catch(Exception ex) {
            ex.printStackTrace();
        } 
    }


}