//import java.util.*;
//import javax.mail.*;
//import javax.mail.internet.*;
//
//import java.util.Properties;
//
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.activation.*;
//public class Email {
//    // email ID of Recipient.
//    String recipient = "recipient@gmail.com";
//    final String username="username";
//    final String password="password";
//
//    // email ID of  Sender.
//    String sender = "sender@gmail.com";
//
//    // using host as localhost
//    String host = "127.0.0.1";
//
//    // Getting system properties
//    Properties properties = new Properties();
//    properties.put();
//
//    // Setting up
//
//
//    // creating session object to get properties
//    Session session = Session.getDefaultInstance(properties);
//
//      try
//    {
//        // MimeMessage object.
//        MimeMessage message = new MimeMessage(session);
//
//        // Set From Field: adding senders email to from field.
//        message.setFrom(new InternetAddress(sender));
//
//        // Set To Field: adding recipient's email to from field.
//        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
//
//        // Set Subject: subject of the email
//        message.setSubject("This is Subject");
//
//        // set body of the email.
//        message.setText("This is a test mail");
//
//        // Send email.
//        Transport.send(message);
//        System.out.println("Mail successfully sent");
//    }
//      catch (MessagingException mex)
//    {
//        mex.printStackTrace();
//    }
//}
