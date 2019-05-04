package org.cheng.eureka.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EurekaServerApplication.class)
public class AppTest {
	@Autowired
	Environment environment;
	@Test
	public void findAllUsers(){
		System.out.println(environment);
		System.out.println(environment.getProperty("JAVA_HOME"));
		System.out.println(environment.getProperty("PATH"));
		System.out.println(environment.getProperty("LOG_HOME"));
		System.out.println(System.getenv().get("MAVEN_HOME"));
		System.out.println(System.getenv().get("LOG_HOME"));
		for(String s:System.getenv().keySet()) {
			System.out.println(s + " - " + System.getenv().get(s));
		}
	}
}
