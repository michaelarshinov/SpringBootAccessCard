package ru.michaelarshinovhome.Template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ru.michaelarshinovhome.Template.util.Gui;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "ru.michaelarshinovhome.Template")
public class TemplateModuleConfiguratorApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(TemplateModuleConfiguratorApplication.class);
	
	@Autowired
	Gui gui;
	
	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(TemplateModuleConfiguratorApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
		logger.info("************************");
	}

	@Override
	public void run(String... args) throws Exception {
		gui.run();
	}

}
