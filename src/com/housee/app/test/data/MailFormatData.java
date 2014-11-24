package com.housee.app.test.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Properties;
 
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.housee.app.henum.KYLType;

import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import static com.housee.app.contants.PGConstants.*;

 
public class MailFormatData {
    
     public static String transaction = "<!DOCTYPE html>\n" +
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
                "                <img src=\"" + host +  "/images/logo.png\" width=\"300\" height=\"100\" alt=\"logo\" id=\"pic\"/>\n" +
                "            </div>\n" +
                "            <div class=\"container\">\n" +
/*change this line content*/                "                <span id=\"welcome\" style=\"position: relative;top: 28px;font-weight: bold;font-size: 17px;font-family: arial;\">Hi Sameer Grover,</span>\n" +
                "                <p id=\"text\" style=\"position: relative;top: 25px;text-align: left;width: 570px;font-family: arial;font-size: 14px;\">The Balance credit in your A/C. No.XX1783 as of 19-Nov-2013 09:12:50 is INR 1300.00.\n" +
                "                Total available balance (including linked deposits and Limit) is INR 51300.00.</p><br/>\n" +
                "                <p style=\"font-size: 13px;\">For any clarifications contact us at 18xxxxxxxx (Toll Free).</p>\n" +
                "                <p style=\"font-size: 13px;\">This service is a part of our constant endeavor to deliver Superior Customer Service Experience to our valued customers. At Housee, we value your feedback. Please write to us at <a href=\"#\">housee@innverse</a>.in,If you would like to view any other details regarding your account, please login to our Housee Web service at <a href=\"#\">http://www.Housee.in</a></p><br/>\n" +
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
                "                    Â© 2013 Housee App Inc. All Rights Reserved.</p>\n" +
                "                    <p class=\"footext\" style=\"font-size: 10px;\"><a href=\"#\" style=\"font-weight: bold;\">Legal Notice | Privacy Policy | Terms of Service</a></p><br/><br/>\n" +
                "            \n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </body>\n" +
                "</html>\n";
                
     public static String KYL = "<!DOCTYPE html>\n" +
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
                "                <img src=\"" + host +  "/images/logo.png\" width=\"300\" height=\"100\" alt=\"logo\" id=\"pic\"/>\n" +
                "            </div>\n" +
                "            <div class=\"container\">\n" +
/*change this line content*/                "                <span id=\"welcome\" style=\"position: relative;top: 28px;font-weight: bold;font-size: 17px;font-family: arial;\">Hi Sameer Grover,</span>\n" +
                "                <p id=\"text\" style=\"position: relative;top: 25px;text-align: left;width: 570px;font-family: arial;font-size: 14px;\">KYL Response Status has been Updated in your Profile.\n" +
                "                <p style=\"font-size: 13px;\">To Know your KYL Status: <a href=\"#\">CLICK HERE</a>.</p>\n" +
                "                <p style=\"font-size: 13px;\">If you are unable to open the above link then click here on <a href=\"#\">http://www.Housee.in/KYL.jsp</a></p><br/>\n" +
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
                "                    Â© 2013 Housee App Inc. All Rights Reserved.</p>\n" +
                "                    <p class=\"footext\" style=\"font-size: 10px;\"><a href=\"#\" style=\"font-weight: bold;\">Legal Notice | Privacy Policy | Terms of Service</a></p><br/><br/>\n" +
                "            \n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </body>\n" +
                "</html>\n";
                
     public static String Pushnotification = "<!DOCTYPE html>\n" +
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
                "                <img src=\"" + host +  "/images/logo.png\" width=\"300\" height=\"100\" alt=\"logo\" id=\"pic\"/>\n" +
                "            </div>\n" +
                "            <div class=\"container\">\n" +
/*change this line content*/                "                <span id=\"welcome\" style=\"position: relative;top: 28px;font-weight: bold;font-size: 17px;font-family: arial;\">Hi Sameer Grover,</span>\n" +
                "                <p id=\"text\" style=\"position: relative;top: 25px;text-align: left;width: 570px;font-family: arial;font-size: 14px;\">Thank you for using Housee and good luck for your game! Remember, Housee is the best place for you to play, win & get cash - from your winning game..\n" +
                "                <p style=\"font-size: 13px;\">Important: Housee takes good care of its users. If you receive any spam in reply to your ad, please report it to <a href=\"#\">support@housee.com</a></p><br/>\n" +
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
                "                    Â© 2013 Housee App Inc. All Rights Reserved.</p>\n" +
                "                    <p class=\"footext\" style=\"font-size: 10px;\"><a href=\"#\" style=\"font-weight: bold;\">Legal Notice | Privacy Policy | Terms of Service</a></p><br/><br/>\n" +
                "            \n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </body>\n" +
                "</html>\n";
                
     public static String Query = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <body>\n" +
                "        <div class=\"main\" style=\"margin-left: auto;margin-right: auto;background-color: #cccccc;height: 95%;position: relative;padding-top: 2%;padding-bottom: 2%;\">\n" +
                "            <div class=\"container\">\n" +
                "            <div class=\"inner\" style=\"background-color: whitesmoke;width: 91%;margin-left: auto;margin-right: auto;font-family: arial;padding-bottom: 3%;\">\n" +
                "                <div id=\"look\" style=\"width: 90%;margin-left: auto;margin-right: auto;border-bottom: #666666 1px dotted;\">\n" +
                "                <div class=\"header\" style=\"background-image:url(logo.png);\">\n" +
                "                <img src=\"" + host +  "/images/logo.png\" width=\"300\" height=\"100\" alt=\"logo\" id=\"pic\"/>\n" +
                "            </div>\n" +
/*change this line content*/                "                <span id=\"welcome\" style=\"position: relative;top: 28px;font-weight: bold;font-size: 17px;font-family: arial;\">Hi Sameer Grover,</span>\n" +
                "                <p id=\"text\" style=\"position: relative;top: 25px;text-align: left;width: 570px;font-family: arial;font-size: 14px;\">You're receiving this email because you have send your Query from your Housee Account.We have Registered your query in our database.</p>\n" +
                "                <p id=\"text\" style=\"position: relative;top: 25px;text-align: left;width: 570px;font-family: arial;font-size: 14px;\">We will reply about your query as soon as possible.</p><br/>\n" +
                "                <p style=\"font-size: 13px;\"> This is a system generated message.</p>\n" +
                "                <p style=\"font-size: 13px;\">Please do not reply to this e-mail.</p><br/>\n" +
                "                <p style=\"font-size: 13px;\">Thanks<br/>\n" +
                "                Housee Team<br/>\n" +
                "                www.Housee.com</p>\n" +
                "</div>\n" +
                "                </div>\n" +
                "                <div class=\"header look1\" style=\"width: 90%;margin-left: auto;margin-right: auto;\">\n" +
                "                    <p class=\"footext\" style=\"font-size: 10px;\">If you need technical assistance, please contact Housee Help. To unsubscribe or otherwise manage your email preferences, visit Housee.com/in/account. Housee may retire online features 30 days after notice has been posted to www.Housee.com/asia/.</p>\n" +
                "                    <p class=\"footext\" style=\"font-size: 10px;\">PRIVACY POLICY: Our Certified Online Privacy Policy gives you confidence whenever you play Housee Casino. To view our complete Privacy Policy, go to privacy.Housee.com or write to: Privacy Policy Administrator, HOUSEE CASINO., 209 Redwood Shores Parkway, Redwood City, CA 94065.\n" +
                "                    Â© 2013 Housee App Inc. All Rights Reserved.</p>\n" +
                "                    <p class=\"footext\" style=\"font-size: 10px;\"><a href=\"#\" style=\"font-weight: bold;\">Legal Notice | Privacy Policy | Terms of Service</a></p><br/><br/>\n" +
                "            \n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </body>\n" +
                "</html>\n";
                
     public static String winner = "<!DOCTYPE html>\n" +
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
                "                <img src=\"" + host +  "/images/logo.png\" width=\"300\" height=\"100\" alt=\"logo\" id=\"pic\"/>\n" +
                "            </div>\n" +
                "            <div class=\"container\">\n" +
/*change this line content*/                "                <span id=\"welcome\" style=\"position: relative;top: 28px;font-weight: bold;font-size: 17px;font-family: arial;\">Hi Sameer Grover,</span>\n" +
                "                <p id=\"text\" style=\"position: relative;top: 25px;text-align: left;width: 570px;font-family: arial;font-size: 14px;\">You won the game. \n</p><br/>\n" +
                "                <p style=\"font-size: 13px;\">(Kindly check your mail.. we will credit your balance in your account within 24 hours).</p>\n" +
                "                <p style=\"font-size: 13px;\">Game-Details: (Room Id-\"110022418\")</p>\n" +
                "                <p style=\"font-size: 13px;\">Current Balance in your A/C as of 19-Nov-2013 is INR 1333.00</p><br/>\n" +
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
                "                    Â© 2013 Housee App Inc. All Rights Reserved.</p>\n" +
                "                    <p class=\"footext\" style=\"font-size: 10px;\"><a href=\"#\" style=\"font-weight: bold;\">Legal Notice | Privacy Policy | Terms of Service</a></p><br/><br/>\n" +
                "            \n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </body>\n" +
                "</html>\n";
               
     public static String kylResponseMail = "<!DOCTYPE html>\n" +
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
/*change this line content*/                "                <span id=\"welcome\" style=\"position: relative;top: 28px;font-weight: bold;font-size: 17px;font-family: arial;\">Dear #USERNAME#,</span>\n" +
     "                <p id=\"text\" style=\"position: relative;top: 25px;text-align: left;width: 570px;font-family: arial;font-size: 14px;\">KYL Response Status has been Updated in your Profile.\n" +
     "                <p style=\"font-size: 13px;\">To Know your KYL Status: <a href=\"#\">CLICK HERE</a>.</p>\n" +
     "                <p style=\"font-size: 13px;\">If you are unable to open the above link then click here on <a href=\"#\">http://www.Housee.in/KYL.jsp</a></p><br/>\n" +
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
     "</html>\n"; //this will be the text of the email
     
     
     public static String kylRequestMail = "<!DOCTYPE html>\n" +
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
/*change this line content*/                "                <span id=\"welcome\" style=\"position: relative;top: 28px;font-weight: bold;font-size: 17px;font-family: arial;\">Dear #USERNAME#, </span>\n" +
     "                <p id=\"text\" style=\"position: relative;top: 25px;text-align: left;width: 570px;font-family: arial;font-size: 14px;\">Your KYL Request  has been updated in our database, we'll reply you ASAP.\n" +
     "                <p style=\"font-size: 13px;font-weight: bold;\">Your KYL Query Type is : #QUERYTYPE# </p>\n" +
     "                <p style=\"font-size: 13px;font-weight: bold;\">Your Kyl Request Message is :  #MESSAGE#</p><br/>\n" +
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
     "</html>\n"; //this will be the text of the email
     
     
     public static String contactUsRequestMail = "<!DOCTYPE html>\n" +
     "<html>\n" +
     "    <body>\n" +
     "        <div class=\"main\" style=\"margin-left: auto;margin-right: auto;background-color: #cccccc;height: 95%;position: relative;padding-top: 2%;padding-bottom: 2%;\">\n" +
     "            <div class=\"container\">\n" +
/*change this line content*/                "                <span id=\"welcome\" style=\"position: relative;top: 28px;font-weight: bold;font-size: 17px;font-family: arial;\">Dear #USERNAME#,</span>\n" +
     "                <p id=\"text\" style=\"position: relative;top: 25px;text-align: left;width: 570px;font-family: arial;font-size: 14px;\">You're receiving this email because you have send your Query from your Housee Account.We have Registered your query in our database.</p>\n" +
     "                <p id=\"text\" style=\"position: relative;top: 25px;text-align: left;width: 570px;font-family: arial;font-size: 14px;\">We will reply about your query as soon as possible.</p><br/>\n" +
     "                <p style=\"font-size: 13px;\"> Your request message is : #MESSAGE#.</p>\n" +
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
     "</html>\n"; //this will be the text of the email
     
     
     public static String contactUSResponseMail = "<!DOCTYPE html>\n" +
     "<html>\n" +
     "    <body>\n" +
     "        <div class=\"main\" style=\"margin-left: auto;margin-right: auto;background-color: #cccccc;height: 95%;position: relative;padding-top: 2%;padding-bottom: 2%;\">\n" +
     "            <div class=\"container\">\n" +
/*change this line content*/                "                <span id=\"welcome\" style=\"position: relative;top: 28px;font-weight: bold;font-size: 17px;font-family: arial;\">Dear #USERNAME#,</span>\n" +
     "                <p id=\"text\" style=\"position: relative;top: 25px;text-align: left;width: 570px;font-family: arial;font-size: 14px;\">You're receiving this email about your Query from your Housee Account.</p>\n" +
     "                <p id=\"text\" style=\"position: relative;top: 25px;text-align: left;width: 570px;font-family: arial;font-size: 14px;\">Your phone no. is : #PHONENUMBER#, and message was  : #MESSAGE#.</p><br/>\n" +
     "				 <p id=\"text\" style=\"position: relative;top: 25px;text-align: left;width: 570px;font-family: arial;font-size: 14px;\">Suggestion about your query is : #SUGGESTION#.</p><br/>\n" +
     "                <p style=\"font-size: 13px;\"> This is a system generated message.</p>\n" +
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
     "</html>\n"; //this will be the text of the email
     
     public static String invitationMail = "<!DOCTYPE html>\n" +
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
/*change this line content*/                "                <span id=\"welcome\" style=\"position: relative;top: 28px;font-weight: bold;font-size: 17px;font-family: arial;\">Hi Sameer Grover,</span>\n" +
     "                <p id=\"text\" style=\"position: relative;top: 25px;text-align: left;width: 570px;font-family: arial;font-size: 14px;\">#FRIENDUSERNAME# sent you an invite for a game of Housee. \n</p><br/>\n" +
     "                <p style=\"font-size: 13px;\">"+
     "                <div class=\"header\" style=\"background-image:url(logo.png);\">\n" +
     "                <img src=\"cid:image2\" width=\"43\" height=\"40\" alt=\"logo\" id=\"pic\"/><a id=\"req\" href=\"#\" style=\"font-weight: bold;color: grey;margin-left: 13px;text-transform: capitalize;\">Join Now</a>" +
     "                </div>\n" +
     "                <p style=\"font-size: 13px;font-weight: bold;\">Game-Details: (Room Id-\"110022418\")</p><br/>\n\n" +
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
     
     

     
     
     
    	public static String getKYLRequestMailData(String userFullName,KYLType queryType, String message){
       		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss z");
       		
       		String regExpressionUsername = "#USERNAME#";
       		String regExpressionQueryType = "#QUERYTYPE#";	
       		String regExpressionMessage = "#MESSAGE#";

       		kylRequestMail = kylRequestMail.replaceAll(regExpressionUsername, userFullName);
       		kylRequestMail = kylRequestMail.replaceAll(regExpressionQueryType, queryType.getMessage());
       		kylRequestMail = kylRequestMail.replaceAll(regExpressionMessage, message);
       		
       		return kylRequestMail;
       	}
    	
    	public static String getKYLResponseMailData(String userFullName){
       		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss z");
       		
       		String regExpressionUsername = "#USERNAME#";

       		kylResponseMail = kylResponseMail.replaceAll(regExpressionUsername, userFullName);
       		
       		return kylResponseMail;
       	}

    	public static String getContactUsRequestMailData(String userFullName, String message){
       		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss z");
       		
       		String regExpressionUsername = "#USERNAME#";
       		String regExpressionMessage = "#MESSAGE#";

       		contactUsRequestMail = contactUsRequestMail.replaceAll(regExpressionUsername, userFullName);
       		contactUsRequestMail = contactUsRequestMail.replaceAll(regExpressionMessage, message);

       		return contactUsRequestMail;
       	}
    	
    	public static String getContactUsResponseMailData(String userFullName, String phone,String message,String suggestion){
       		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss z");
       		
       		String regExpressionUsername = "#USERNAME#";
       		String regExpressionPhone = "#PHONENUMBER#";
       		String regExpressionMessage = "#MESSAGE#";
       		String regExpressionSuggestion = "#SUGGESTION#";

       		contactUSResponseMail = contactUSResponseMail.replaceAll(regExpressionUsername, userFullName);
       		contactUSResponseMail = contactUSResponseMail.replaceAll(regExpressionPhone, phone);
       		contactUSResponseMail = contactUSResponseMail.replaceAll(regExpressionUsername, message);
       		contactUSResponseMail = contactUSResponseMail.replaceAll(regExpressionSuggestion, suggestion);

       		return contactUSResponseMail;
       	}
    	
}