package mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.jboss.logging.Logger;

import beans.UserBean;


@MessageDriven(activationConfig = { 
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue/MyQueue")
})
public class MyQueueConsumerMDB implements MessageListener {

    Logger log = Logger.getLogger(getClass());
    
    @EJB
    UserBean userBean;
    
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    public MyQueueConsumerMDB() {
    }
	
    public void onMessage(Message message) {
        ObjectMessage txt = (ObjectMessage) message;
		MailEvent mailEvent;
		try {
			mailEvent = (MailEvent) txt.getObject();
			userBean.sendEmail(mailEvent);
		} catch (JMSException e) {
			e.printStackTrace();
		}
    }
}
