package org.cheng.boot.service.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderTest {

	public static void main(String[] args) {
		BCryptPasswordEncoder e = new BCryptPasswordEncoder();
		System.out.println(e.encode("123456"));
	}

}
