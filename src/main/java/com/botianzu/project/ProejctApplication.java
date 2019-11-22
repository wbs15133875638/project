package com.botianzu.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication
@MapperScan("com.botianzu.project.mapper")
public class ProejctApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProejctApplication.class, args);
	}

}
