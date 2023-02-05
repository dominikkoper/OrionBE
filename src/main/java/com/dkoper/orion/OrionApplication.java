package com.dkoper.orion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {FlywayAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class}, scanBasePackages = "com.dkoper.orion")
@ComponentScan({"com.dkoper.orion.config", "com.dkoper.orion.controller", "com.dkoper.orion.service", "com.dkoper.orion.mapper"})
public class OrionApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrionApplication.class, args);
	}

}
