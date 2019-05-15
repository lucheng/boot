package org.lucheng.boot.hi.repository.security;

import org.lucheng.boot.hi.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
}
