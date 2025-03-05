package com.alxbryann.foc.model;

/**
 *
 * @author barr2
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {

    private static final String EMAIL_FROM = "noreplybudgettrack@gmail.com";
    private static final String PASSWORD_FROM = "swtb zscr jhfm dclg";

    private Properties mProperties;
    private Session mSession;

    public Mailer() {
        System.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        System.setProperty("jdk.tls.client.protocols", "TLSv1.2");

        mProperties = new Properties();
        mProperties.put("mail.smtp.auth", "true");
        mProperties.put("mail.smtp.starttls.enable", "true");
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.port", "587");
        mProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        mSession = Session.getInstance(mProperties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_FROM, PASSWORD_FROM);
            }
        });
    }

    public void sendEmail(String subject, String content) {
        try {
            MimeMessage mMail = new MimeMessage(mSession);
            mMail.setFrom(new InternetAddress(EMAIL_FROM));
            mMail.setRecipient(Message.RecipientType.TO, new InternetAddress("bryanalexanderbogota@gmail.com"));
            mMail.setSubject(subject);
            mMail.setText(content);
            mMail.setContent(content, "text/html; charset=utf-8");
            Transport.send(mMail);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}
