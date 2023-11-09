package ru.michaelarshinovhome.Template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableSwagger2
@EnableScheduling
@EnableCaching
public class TemplateModuleApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TemplateModuleApplication.class, args);
		System.out.println("************************");
	}

}
