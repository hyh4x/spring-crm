package no.group.springdemo.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import no.group.springdemo.entity.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {
	
	private SessionFactory securitySessionFactory;
	
	public RoleDAOImpl(SessionFactory securitySessionFactory) {
		this.securitySessionFactory = securitySessionFactory;
		
	}

	@Override
	public Role findByRole(String role) {
		
		Session currentSession = securitySessionFactory.getCurrentSession();
		
		Query<Role> query = currentSession.createQuery("from Role where role = :userRole", Role.class);
		
		query.setParameter("userRole", role);
		
		Role resultRole = null;
		
		try {
			resultRole = query.getSingleResult();
		} catch(Exception e) {
			resultRole = null;
		}
		
		return resultRole;
		
	}

}
