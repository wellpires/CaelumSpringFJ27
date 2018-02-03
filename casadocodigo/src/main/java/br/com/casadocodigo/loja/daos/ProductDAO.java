package br.com.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Product;

@Repository
public class ProductDAO {

	@PersistenceContext
	private EntityManager manager = null;

	public void save(Product product) {
		manager.persist(product);
	}

	public List<Product> list() {
		TypedQuery<Product> productQuery = manager.createQuery("SELECT DISTINCT p FROM Product p JOIN FETCH p.prices",
				Product.class);
		return productQuery.getResultList();
	}

	public Product find(Long id) {
		return manager
				.createQuery("SELECT DISTINCT p FROM Product p JOIN FETCH p.prices WHERE p.id = :id", Product.class)
				.setParameter("id", id).getSingleResult();
	}

	public BigDecimal sumPricesPerType(BookType bookType) {
		TypedQuery<BigDecimal> query = manager.createQuery(
				"SELECT SUM(price.value) FROM Product p JOIN p.prices price WHERE price.bookType = :bookType", BigDecimal.class);
		query.setParameter("bookType", bookType);
		return query.getSingleResult();
	}

}
