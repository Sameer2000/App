package com.housee.app.mail;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.housee.app.contants.SystemSettingConstants;
import com.housee.app.henum.KYLType;
import com.housee.app.test.data.MailFormatData;
import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

public class KYLRequestMail extends SendMailAPI {

	String username;
	KYLType queryType;
	String message;
	
	/**
     * Sends an email with a PDF attachment.
     */
    public void email(String username, KYLType queryType, String message) {
    	this.message = message;
    	this.queryType = queryType;
    	this.username = username;
    	
        String recipient = SystemSettingConstants.ADMIN_EMAIL_ID; //replace this with a valid recipient email address
        String content = MailFormatData.getKYLRequestMailData(username,queryType,message);
         
		 String subject = "Kyl Request"; //this will be the subject of the email
		 
        ByteArrayOutputStream outputStream = null;
         
        try {           
            //construct the text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(content);
            textBodyPart.setContent(content, "text/html");
             
            //construct the mime multi part
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            /*mimeMultipart.addBodyPart(pdfBodyPart);*/
             
            //create the sender/recipient addresses
            InternetAddress iaSender = new InternetAddress(sender);
            InternetAddress iaRecipient = new InternetAddress(recipient);
             
            //construct the mime message
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setSender(iaSender);
            mimeMessage.setSubject(subject);
            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
            mimeMessage.setContent(mimeMultipart);
             
            //send off the email
            Transport.send(mimeMessage);
             
            System.out.println("sent from " + sender + 
                    ", to " + recipient + 
                    "; server = " + smtpHost + ", port = " + smtpPort);         
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            //clean off
            if(null != outputStream) {
                try { outputStream.close(); outputStream = null; }
                catch(Exception ex) { }
            }
        }
    }
     
    /**
     * Writes the content of a PDF file (using iText API)
     * to the {@link OutputStream}.
     * @param outputStream {@link OutputStream}.
     * @throws Exception
     */
    /*public void writePdf(OutputStream outputStream) throws Exception {
//        Document document = new Document();
//        PdfWriter.getInstance(document, outputStream);
//         
//        document.open();
//         
//        document.addTitle("Test PDF");
//        document.addSubject("Testing email PDF");
//        document.addKeywords("iText, email");
//        document.addAuthor("Jee Vang");
//        document.addCreator("Jee Vang");
//         
//        Paragraph paragraph = new Paragraph();
//        paragraph.add(new Chunk("hello!"));
//        document.add(paragraph);
//         
//        document.close();
    	
    	
    	try {
    	    String k = "<html><body> this is my jasper report and its come from my pdf click</body></html>";
    	    Document document = new Document();
    	    PdfWriter.getInstance(document, outputStream);
    	    document.open();
    	    HTMLWorker htmlWorker = new HTMLWorker(document);
    	    htmlWorker.parse(new StringReader(k));
    	    document.close();
    	    outputStream.close();
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}*/
    	
    /*}*/
     
    /**
     * Main method.
     * @param args No args required.
     */
    /*public static void main(String[] args) {
    	iTextEmailPDF demo = new iTextEmailPDF();
        demo.email();
    }*/
}