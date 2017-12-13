package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.inject.*;

import beans.UserBean;
import dto.UserDTO;
import entities.User;
import java.util.*;
/**
 * Servlet implementation class admin
 */
@WebServlet("/admin")
public class admin extends HttpServlet {
	
	@Inject
	UserBean userBean;
	
	private static final long serialVersionUID = 1L;

    public admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		java.util.List<User> users = this.userBean.getAllUsers();
			
		request.setAttribute("users", users);
		request.getRequestDispatcher("/admin.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}
