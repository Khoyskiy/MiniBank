package com.minibank.GmailSender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class GmailSender {
    private String from = "MiniBankop@gmail.com";
    private  String host = "smtp.gmail.com";
    private String smtpPort = "465";

    public void send(String mess, String email){

        Properties props= new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");

        Session session = Session.getInstance(
                props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,"ciri qzbh nknx gurp");
                    }
                }
        );
        session.setDebug(true);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(email));
            message.setSubject("Check by mail");
            message.setText("Your Pincode for Mail Login - "+ mess);

            Transport.send(message);

            System.out.println("Correct");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    }
