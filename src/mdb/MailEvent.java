package mdb;

import java.io.Serializable;

public class MailEvent implements Serializable{
	private String to;
    private String message;
    private String subject;
    
    public MailEvent() {}
    
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
}
