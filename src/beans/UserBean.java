package beans;

import entities.User;
import mdb.MailEvent;
import mdb.MessageProducerBean;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dto.UserDTO;

@Stateless
@LocalBean
public class UserBean {

	@EJB
    MessageProducerBean msgProducer;
	
	@Resource
    private TimerService timerService;
	
    @PersistenceContext(unitName="entityManager")
    private EntityManager entityManager;

    @Inject
    private Event<mdb.MailEvent> eventProducer;
    
    public java.util.List<User> getAllUsers() {
        Query q = entityManager.createQuery("select x from User x");
        java.util.List<User> u = q.getResultList();
        return u;
    }
    
    public String addUser(User user) {
    	entityManager.persist(user);
		return user.getId().toString();
    }

	public void deleteUser(User u) {
		entityManager.remove(entityManager.contains(u) ? u : entityManager.merge(u));			
	}

	public void updateUser(UserDTO udto) {
		User u = entityManager.getReference(User.class, udto.getId());
		u.setEmail(udto.getEmail());
		u.setFirstName(udto.getFirstname());
		u.setLastName(udto.getLastname());
		u.setUserName(udto.getUsername());
		entityManager.persist(u);
	}
	
	public Long checkUser(User u) {
		Query q = entityManager.createQuery("select x from User x where x.email=:email and x.password=:password");
		q.setParameter("email", u.getEmail());
		q.setParameter("password", u.getPassword());
		User user = (User) q.getSingleResult();
		if(user != null) {
			MailEvent event = new MailEvent();
			event.setMessage("");
			event.setSubject("");
			event.setTo("emailforanypurposes@gmail.com");
			this.scheduleEmail(event);
			return user.getId();
		}
		return 0l;
	}
	
	public void sendEmail(mdb.MailEvent event) {
        eventProducer.fire(event);
    }
	
	public void scheduleEmail(MailEvent event) {
        TimerConfig tf = new TimerConfig(event, false);        
        timerService.createSingleActionTimer(new Date(), tf);
    }
    
    @Timeout
    public void onTimeout(Timer timer) {
      MailEvent event = (MailEvent)timer.getInfo();
      msgProducer.sendMessage(event);
    }
}
