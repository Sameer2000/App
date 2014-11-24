package com.housee.app.mail;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import com.housee.app.contants.SystemSettingConstants;

public class Push_notification_mail extends SendMailAPI {
	String userEmailID,shortMessage,longMessage,senderName;
	Long roomId = null;
    /**
     * Sends an email with a PDF attachment.
     */
    public void sendEmail(String userEmailID,String shortMessage,String longMessage , String senderName) {
    	this.userEmailID = userEmailID;
    	this.shortMessage = shortMessage;
    	this.longMessage = longMessage;
    	this.senderName = senderName;
    	
        String notificationMail = "<!DOCTYPE html>\n" +
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
/*change this line content*/                "                <span id=\"welcome\" style=\"position: relative;top: 28px;font-weight: bold;font-size: 17px;font-family: arial;\"></span>\n" +
        "                <p style=\"font-size: 13px;\">"+
        "                <div class=\"header\" style=\"background-image:url(logo.png);\">\n" +
        "                <p style=\"font-size: 13px;font-weight: bold;\">"+shortMessage+"</p><br/>\n\n" +
        "                <p style=\"font-size: 13px;\"> "+longMessage+"</p><br/><br/>\n" +
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
        
        
    	
    	String strSubject = "Housee Notification";
    	
        String recipients[] = {userEmailID ,SystemSettingConstants.ADMIN_EMAIL_ID}; //replace this with a valid recipient email address
        String subject = strSubject; //this will be the subject of the email
         
         
        try {           
            //construct the text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setContent(notificationMail, "text/html");
            
                         
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
            mimeMessage.setRecipients(Message.RecipientType.TO, userEmailID);
            mimeMessage.setContent(mimeMultipart);
             
            //send off the email
            Transport.send(mimeMessage);
             
                    
        } catch(Exception ex) {
            ex.printStackTrace();
        } 
    }


}
