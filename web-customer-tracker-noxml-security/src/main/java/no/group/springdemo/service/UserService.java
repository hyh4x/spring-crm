package no.group.springdemo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import no.group.springdemo.entity.User;
import no.group.springdemo.user.CrmUser;

public interface UserService extends UserDetailsService{
	
	User findByUsername(String username);
	
	void save(CrmUser crmUser);

}
