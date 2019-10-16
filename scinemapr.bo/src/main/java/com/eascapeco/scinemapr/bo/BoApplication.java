package com.eascapeco.scinemapr.bo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.eascapeco.scinemapr")
public class BoApplication {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(BoApplication.class, args);
	}

}