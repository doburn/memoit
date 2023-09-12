package com.doburn.memoit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MemoitApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemoitApplication.class, args);
	}

}
