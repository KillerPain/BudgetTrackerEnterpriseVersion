package mdb;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Singleton
public class MailService {
    
	@Resource(mappedName = "java:jboss/mail/MyMail")
    private Session mailSession;
     
    @Asynchronous
    @Lock(LockType.READ)
    public void sendMail(@Observes(during = TransactionPhase.AFTER_SUCCESS) MailEvent event) {
        try {
            MimeMessage m = new MimeMessage(mailSession);
            Address[] to = new InternetAddress[] {new InternetAddress(event.getTo())};
            	
            m.setRecipients(Message.RecipientType.TO, to);
            m.setSubject(event.getSubject());
            m.setSentDate(new java.util.Date());
            m.setContent(event.getMessage(),"text/plain");
             
            Transport.send(m);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
   }
}
