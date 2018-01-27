package br.com.casadocodigo.loja.daos;

import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.User;

@Repository
public class UserDAO implements UserDetailsService {

	@PersistenceContext
	private EntityManager em = null;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		String jpql = "SELECT u FROM User u WHERE u.login = :login";
		
		User users = em.createQuery(jpql, User.class).setParameter("login", username).getSingleResult();
		
		if(Objects.isNull(users)){
			throw new UsernameNotFoundException("O usuário " + username + " não existe");
		}
		
		return users;
	}

}
