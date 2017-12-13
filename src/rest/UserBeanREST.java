package rest;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.UserBean;
import dto.UserDTO;
import entities.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Stateless
@LocalBean
@Path("/profile")
public class UserBeanREST {
	
	@Inject
	UserBean user;

	@PersistenceContext(unitName="entityManager")
	private EntityManager entityManager;

	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/login")	
	public UserDTO login(User u) {
		Long id = this.user.checkUser(u);
    	UserDTO userDTO = new UserDTO();
		userDTO.setEmail(u.getEmail());
		userDTO.setUsername(u.getUserName());
		userDTO.setId(id);
		return userDTO;
	}
    
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/register")
	public String register(User user) {		
		this.user.addUser(user);
		return "ok";
	}
    
	
	@Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/user/{id}")
	public UserDTO getUser(@PathParam("id") Long id) {
		// TODO Auto-generated method stub
		Query q = entityManager.createQuery("select x from User x where x.id=:id");	
		q.setParameter("id", id);
		User u = (User) q.getSingleResult();
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(u.getEmail());
		userDTO.setUsername(u.getUserName());
		userDTO.setFirstname(u.getFirstName());
		userDTO.setLastname(u.getLastName());
		return userDTO;
	}

}
