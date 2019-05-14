package org.cheng.boot.auth.repository.security;

import org.cheng.boot.auth.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
}
