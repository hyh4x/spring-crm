package no.group.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import no.group.springdemo.DAO.CustomerDAO;
import no.group.springdemo.entity.Customer;
import no.group.springdemo.util.SortBy;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Transactional(value="transactionManager")
	@Override
	public List<Customer> getCustomers(SortBy sortBy) {
		return customerDAO.getCustomers(sortBy);
	}

	@Transactional(value="transactionManager")
	@Override
	public void saveCustomer(Customer customer) {
		customerDAO.saveCustomer(customer);
	}

	@Transactional(value="transactionManager")
	@Override
	public Customer getCustomer(int id) {
		return customerDAO.getCustomer(id);
	}

	@Transactional(value="transactionManager")
	@Override
	public void deleteCustomer(int id) {
		customerDAO.deleteCustomer(id);
	}

	@Transactional(value="transactionManager")
	@Override
	public List<Customer> searchCustomer(String searchName) {
		return customerDAO.searchCustomer(searchName);
	}

}
