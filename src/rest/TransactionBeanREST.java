package rest;

import java.util.List;

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

import beans.TransactionBean;
import dto.TransactionDTO;
import entities.Transaction;
import entities.User;

@Stateless
@LocalBean
@Path("/app")
public class TransactionBeanREST {
	@PersistenceContext(unitName="entityManager")
	private EntityManager entityManager;
	
	@Inject
	TransactionBean bean;
	
	@Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/add/{id}")
	public String add(Transaction t, @PathParam("id") Long id) {
		Query q = entityManager.createQuery("select x from User x where x.id=:id");
		q.setParameter("id", id);
		User u = (User) q.getSingleResult();
		t.setUser(u);
		bean.addTransaction(t);
		return "ok";
	}
	
	@Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/get")
	public List<TransactionDTO> get() {
		return bean.getAllTransactions();
	}
	
}
