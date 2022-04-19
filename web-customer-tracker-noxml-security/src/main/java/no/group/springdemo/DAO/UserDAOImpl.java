package no.group.springdemo.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import no.group.springdemo.entity.User;

@Repository
public class UserDAOImpl implements UserDAO{

	@Autowired
	private SessionFactory securitySessionFactory;
	
	@Override
	public User findByUsername(String username) {
		Session currentSession = securitySessionFactory.getCurrentSession();
		
		Query<User> query = currentSession.createQuery("from User where username=:uName", User.class);
		query.setParameter("uName", username);
		User user = null;
		
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			user = null;
		}
		
		return user;
	}

	@Override
	public void save(User user) {
		Session currentSession = securitySessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(user);
	}

}
