package no.group.springdemo.DAO;

import no.group.springdemo.entity.User;

public interface UserDAO {

	User findByUsername(String username);

	void save(User user);
	
}
