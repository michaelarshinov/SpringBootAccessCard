package ru.michaelarshinovhome.Template.config.security;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import ru.michaelarshinovhome.Template.service.MAHConnectionService;

@Configuration
@ComponentScan
@EnableAutoConfiguration
//@EnableWebSecurity(debug = true) // для того чтобы увидеть Security filter chain и добавить свой фильтр в цепочку
@ConditionalOnExpression(value = "${auth.jwt.enabled}==true")
public class SecurityConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);
	
	public static String SERVER_SERVLET_CONTEXT_PATH="";
	
    @Value("${server.servlet.context-path}")
    public void setNameStatic(String name){
    	SecurityConfiguration.SERVER_SERVLET_CONTEXT_PATH = name;
    }
	
	public static String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
			"/v2/api-docs",
			"/swagger-resources",
			"/swagger-resources/**",
			"/configuration/ui",
			"/configuration/security",
			"/swagger-ui.html",
			"/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
			"/v3/api-docs/**",
			"/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };
	
	public static String[] getWhiteList() {
		return (String[]) List.of(AUTH_WHITELIST).stream()
		        .map(SERVER_SERVLET_CONTEXT_PATH::concat).toArray(String[]::new);
	}
	
	@Autowired
	MAHConnectionService mahConnectionService;
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	logger.info("AUTH IS ENABLED"+mahConnectionService);
        http.authorizeRequests().antMatchers(getWhiteList()).permitAll()
                /*.antMatchers("/users/**", "/settings/**").hasAuthority("Admin")
                .hasAnyAuthority("Admin", "Editor", "Salesperson", "Shipper")*/
         //       .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .cors().and()
                //.addFilterAfter(new JwtAuthenticationTokenFilter(mahConnectionService), ExceptionTranslationFilter.class);
                .addFilterAfter(new JwtAuthenticationTokenFilter(mahConnectionService), BasicAuthenticationFilter.class);
        http.headers().frameOptions().sameOrigin();
        return http.build();
    }
 
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }
 
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        config.setAllowedMethods(List.of("*"));
        source.registerCorsConfiguration("/**", config);
        return source;
	}
}