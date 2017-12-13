package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dto.TransactionDTO;
import entities.Transaction;

@Stateless
@LocalBean
public class TransactionBean {
	@PersistenceContext(unitName="entityManager")
    private EntityManager entityManager;

    public java.util.List<TransactionDTO> getAllTransactions() {
        Query q = entityManager.createQuery("select x from Transaction x");
        java.util.List<Transaction> u = q.getResultList();
        
        List<TransactionDTO> all = new ArrayList<TransactionDTO>();
        for(Transaction t: u){
        	TransactionDTO tt = new TransactionDTO();
        	tt.setContent(t.getContent());
        	tt.setId(t.getId());
        	tt.setPrice(t.getPrice());
        	all.add(tt);
        }
        
        return all;
    }
    
    public String addTransaction(Transaction transaction) {
    	entityManager.persist(transaction);
		return transaction.getContent();
    }
}
