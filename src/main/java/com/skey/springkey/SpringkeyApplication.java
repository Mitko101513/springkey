package com.skey.springkey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "com.skey.springkey")
@EnableCaching
public class SpringkeyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringkeyApplication.class, args);
	}
}
