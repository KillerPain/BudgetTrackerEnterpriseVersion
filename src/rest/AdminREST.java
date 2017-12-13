package rest;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.UserBean;
import dto.UserDTO;
import entities.User;

@Stateless
@LocalBean
@Path("/admin")
public class AdminREST {
	@Inject
	UserBean userbean;
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/hello")
	public String hello() {
		return "hello";
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Path("/addUser")
	public String addUser(User u) {		
		String newID = this.userbean.addUser(u);	
		return "{\"id\": \"" + newID +"\"}";
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Path("/deleteUser")
	public String deleteUser(User u) {		
		this.userbean.deleteUser(u);			
		return "{\"status\": \"ok\"}";
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Path("/updateUser")
	public String updateUser(UserDTO udto) {
		this.userbean.updateUser(udto);			
		return "{\"status\": \"ok\"}";
	}
	
	
	
	

}
