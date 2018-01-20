package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Product;

@Repository
public class ProductDAO {
	
	@PersistenceContext
	private EntityManager manager = null;
	
	public void save(Product product){
		manager.persist(product);
	}
	
	public List<Product> list(){
		TypedQuery<Product> productQuery = manager.createQuery("SELECT DISTINCT p FROM Product p JOIN FETCH p.prices", Product.class);
		return productQuery.getResultList();
	}

	public Product find(Long id) {
		return manager.createQuery("SELECT DISTINCT p FROM Product p JOIN FETCH p.prices WHERE p.id = :id",Product.class).setParameter("id", id).getSingleResult();
	}

}
