/**
 * Copyright (c) All rights reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium is strictly prohibited
 * @Author Rajatt( Rajat tripathi )
 */

import java.io.File;
import java.io.IOException;
import java.util.Properties;

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

public class SendEmail {

    public static void main(String[] args)  {

        // Recipient's email ID needs to be mentioned.
        String[] to = {"pruthvikumar.p24@gmail.com","krupa@codeops.tech","sudharshan@codeops.tech","indukurimr@azuga.com","adarshs@azuga.com","aparajitam@azuga.com","ashoop@azuga.com",
                "dushyants@azuga.com","kartiks@azuga.com","lokanathk@azuga.com","pruthvikp@azuga.com","rajatt@azuga.com",
                "rishabh@azuga.com","satvikm@azuga.com","suryaps@azuga.com","vijayyv@azuga.com"};


        // Sender's email ID needs to be mentioned
        String from = "tripathirajat1999@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");


        // Get the Session object.// and pass
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("tripathirajat1999@gmail.com", "aogjtywgzjpzqopx");

            }

        });
        for(int i=0;i<to.length;i++) {
            //session.setDebug(true);
            try {
                // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(session);

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));

                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));

                // Set Subject: header field
                message.setSubject("System generated email for charts of Basketball API");

                Multipart multipart = new MimeMultipart();

                MimeBodyPart attachmentPart = new MimeBodyPart();

                MimeBodyPart textPart = new MimeBodyPart();

                try {

                    File f = new File("/Users/azuga/Desktop/ApicallMethod2/charts.zip");

                    attachmentPart.attachFile(f);
                    textPart.setText(" Hi\n" +
                            "I am adding charts zip file for your reference.\n" +
                            "Thanks and regards \n Rajat tripathi");
                    multipart.addBodyPart(textPart);
                    multipart.addBodyPart(attachmentPart);

                } catch (IOException e) {

                    e.printStackTrace();

                }

                message.setContent(multipart);

                System.out.println("sending...");
                // Send message
                Transport.send(message);
                System.out.println("Sent message successfully....");
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }

        // Recipient's email ID needs to be mentioned.


    }

}
