package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"repository", "controllers", "services"})
public class KameleoonApplication {

	public static void main(String[] args) {
		SpringApplication.run(KameleoonApplication.class, args);
	}

}
