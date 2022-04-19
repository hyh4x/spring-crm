package no.group.springdemo.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import no.group.springdemo.entity.Customer;
import no.group.springdemo.util.SortBy;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers(SortBy sortBy) {
		
		Session session = sessionFactory.getCurrentSession();
		
		String fieldName = null;
		
		switch (sortBy) {
			case FIRST_NAME: fieldName = "firstName"; break;
			case LAST_NAME: fieldName = "lastName"; break;
			case EMAIL: fieldName = "email"; break;
			default: fieldName = "lastName";
		}
		
		String queryString = "from Customer order by "+fieldName;
		
		Query<Customer> query = session.createQuery(queryString,
													Customer.class);
		
		
		List<Customer> result = query.getResultList();
		
		return result;
	}

	@Override
	public void saveCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Customer.class, id);
	}

	@Override
	public void deleteCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();
		Customer toBeDeleted = session.get(Customer.class, id);
		session.delete(toBeDeleted);
	}

	@Override
	public List<Customer> searchCustomer(String searchName) {
		Session session = sessionFactory.getCurrentSession();
		Query query = null;
		if(searchName!=null && searchName.trim().length()>0) {
			query = session.createQuery("from Customer where "
					+ "lower(firstName) like :searchName "
					+ "or lower(lastName) like :searchName "
					+ "order by lastName",Customer.class);
			query.setParameter("searchName", "%"+searchName.toLowerCase()+"%");
		} else {
			query = session.createQuery("from Customer", Customer.class);
		}
		
		return query.getResultList();
	}

}
