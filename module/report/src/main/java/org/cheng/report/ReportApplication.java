package org.cheng.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@SpringBootApplication
//@ImportResource("classpath:component_beans.xml")  
public class ReportApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ReportApplication.class, args);
	}
	
}
