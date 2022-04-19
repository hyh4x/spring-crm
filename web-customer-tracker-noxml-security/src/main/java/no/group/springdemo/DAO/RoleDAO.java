package no.group.springdemo.DAO;

import no.group.springdemo.entity.Role;

public interface RoleDAO {
	Role findByRole(String role);
}
