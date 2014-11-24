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

import org.jfree.data.xy.YisSymbolic;

import com.housee.app.contants.SystemSettingConstants;
import com.housee.app.mail.Testmail;
import com.housee.app.test.data.MailFormatData;
import com.housee.app.test.data.PDFMailFormatData;
import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
 
/**
 * Email with PDF example.
 * <br><br>
 * Email sending code adapted from http://www.java-tips.org/other-api-tips/javamail/how-to-send-an-email-with-a-file-attachment.html.
 * @author Jee Vang
 *
 */
public class RedeemEmailPDF  extends SendMailAPI {
     
	String userFullName, rechargeAmt, availableBalAmnt;
    /**
     * Sends an email with a PDF attachment.
     */
    public void email(String userFullName,String rechargeAmt,String availableBalAmnt) {
    	this.userFullName = userFullName;
    	this.rechargeAmt = rechargeAmt;
    	this.availableBalAmnt = availableBalAmnt;
    	
    	String strSubject = "Housee Redeem Amount!";
    	
        String recipients[] = {"sumit.jagia@gmail.com", SystemSettingConstants.ADMIN_EMAIL_ID}; //replace this with a valid recipient email address
        String content = PDFMailFormatData.getRechargeTransactionSuccessMailData(userFullName, rechargeAmt, availableBalAmnt); //this will be the text of the email
        String subject = strSubject; //this will be the subject of the email

        ByteArrayOutputStream outputStream = null;
         
        try {           
            //construct the text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setContent(content, "text/html");
             
            //now write the PDF content to the output stream
            outputStream = new ByteArrayOutputStream();
            writePdf(outputStream);
            byte[] bytes = outputStream.toByteArray();
             
            //construct the pdf body part
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("test.pdf");
                         
            //construct the mime multi part
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);
             
            //create the sender/recipient addresses
            InternetAddress iaSender = new InternetAddress(sender);
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
            mimeMessage.setRecipients(Message.RecipientType.TO, addressTo);
            mimeMessage.setContent(mimeMultipart);
             
            //send off the email
            Transport.send(mimeMessage);
             
            System.out.println("sent from " + sender + 
                    ", to " + recipients.toString() + 
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
    public void writePdf(OutputStream outputStream) throws Exception {
    	
    	try {
    	    String k = PDFMailFormatData.getRechargeTransactionSuccessMailData(userFullName, rechargeAmt, availableBalAmnt);
    	    Document document = new Document();
    	    PdfWriter.getInstance(document, outputStream);
    	    document.open();
    	    HTMLWorker htmlWorker = new HTMLWorker(document);
    	    htmlWorker.parse(new StringReader(k));
    	    document.close();
    	    outputStream.close();
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}
    	
    }
     
}