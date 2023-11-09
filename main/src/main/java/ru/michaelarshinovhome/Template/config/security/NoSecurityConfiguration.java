package ru.michaelarshinovhome.Template.config.security;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@ConditionalOnExpression("${auth.jwt.enabled}==false")
public class NoSecurityConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(NoSecurityConfiguration.class);
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		logger.info("NO AUTH");
		return security.httpBasic()
				.disable().csrf().disable()
				.cors().and()
				.build();
	}
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        /*UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;*/
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        config.setAllowedMethods(List.of("*"));
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
