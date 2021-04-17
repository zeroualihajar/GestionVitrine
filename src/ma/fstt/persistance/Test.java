package ma.fstt.persistance;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit");
				EntityManager em = emf.createEntityManager();
		
		
		
		em.getTransaction().begin();
		Category  cat = new Category(0,"Electronic", null);
		em.persist(cat);
		em.getTransaction().commit();
		
	}

}
