package ru.michaelarshinovhome.Template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.michaelarshinovhome.Template.util.Gui;

@Configuration
public class AppConfig {
    @Bean
    public Gui gui() {
        return new Gui();
    }
}
