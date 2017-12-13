package entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="people")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id", unique=true, nullable = false)
    private Long id;
    
    @Column(name="username", nullable = true)
    private String username;
    
    @Column(name="firstname", nullable = true)
    private String firstname;
    
    @Column(name="lastname", nullable = true)
    private String lastname; 

	@Column(name="email", unique = true, nullable = false)
    private String email;
      
    @Column(name="password", nullable = false)
    private String password;
    
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;
    
    public User() {}
    
    public User(String email2, String password2) {
		this.email = email2;
		this.password = password2;		
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String name) {
		this.username = name;
	}
	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String name) {
		this.firstname = name;
	}

	public String getLastName() {
		return lastname;
	}

	public void setLastName(String name) {
		this.lastname = name;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    
}
