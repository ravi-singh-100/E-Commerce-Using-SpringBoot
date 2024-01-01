package com.ECommerce.major;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MajorApplication {


	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		SpringApplication.run(MajorApplication.class, args);
		System.out.println("Server has been started");
		System.out.println(bCryptPasswordEncoder.encode("12345"));
	}

}
