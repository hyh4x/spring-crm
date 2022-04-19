package no.group.springdemo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import no.group.springdemo.DAO.RoleDAO;
import no.group.springdemo.DAO.UserDAO;
import no.group.springdemo.entity.Role;
import no.group.springdemo.entity.User;
import no.group.springdemo.user.CrmUser;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleDAO roleDao;
	
//	public UserServiceImpl(UserDAO userDao,
//							BCryptPasswordEncoder passwordEncoder,
//							RoleDAO roleDao) {
//		this.userDao = userDao;
//		this.passwordEncoder = passwordEncoder;
//		this.roleDao = roleDao;
//	}
	
	

	@Override
	@Transactional(value="securityTransactionManager")
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	@Transactional(value="securityTransactionManager")
	public void save(CrmUser crmUser) {
		User user = new User();
		user.setUsername(crmUser.getUsername());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setFirstName(crmUser.getFirstName());
		user.setLastName(crmUser.getLastName());
		user.setEmail(crmUser.getEmail());
		
		Role employee = roleDao.findByRole("ROLE_EMPLOYEE");
		user.setRoles(Arrays.asList(employee));
		userDao.save(user);
	}

	@Override
	@Transactional(value="securityTransactionManager")
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("InvalidUsernameOrPassword");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(),
						user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection <? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles
					.stream()
					.map(role -> new SimpleGrantedAuthority(role.getRole()))
					.collect(Collectors.toList());
	}
	
}














