package rest;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import dto.UserDTO;
import entities.User;

public interface IUserBeanREST {
	public void logout(String email);
	public UserDTO login(String email,  String password);
	String register(User exclusion);
	
}
