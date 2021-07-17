package mena.gov.bf.alerteNotification.service;

import mena.gov.bf.alerteNotification.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Transactional
@Service
public class MailService {

    private final Logger log = LoggerFactory.getLogger( AlerteNotificationService.class );

    private void sendmail() throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tutorialspoint@gmail.com", "<your password>");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("tutorialspoint@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("tutorialspoint@gmail.com"));
        msg.setSubject("Tutorials point email");
        msg.setContent("Tutorials point email", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Tutorials point email", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(null);
        MimeBodyPart attachPart = new MimeBodyPart();

        //attachPart.attachFile("/var/tmp/image19.png");
        multipart.addBodyPart(null);
        msg.setContent(multipart);
        Transport.send(msg);
    }
}
