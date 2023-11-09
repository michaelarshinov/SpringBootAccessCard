package ru.michaelarshinovhome.Template;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ru.michaelarshinovhome.Template.dto.DtoWrapper;
import ru.michaelarshinovhome.Template.dto.VCRConnectionAccountModelResultContainer;
import ru.michaelarshinovhome.Template.dto.VCRConnectionLogin;
import ru.michaelarshinovhome.Template.dto.wrapped.PluginInfoModelDtoWrapped;
import ru.michaelarshinovhome.Template.service.ModuleService;
import ru.michaelarshinovhome.Template.service.VCRConnectionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MAHConnectionServiceImplTest {
	@Autowired
	MAHConnectionService service;
	
	@Autowired
	ModuleService moduleService;
	
	@Value("${mah.server.login}")
	private String login;

	@Value("${mah.server.password}")
	private String password;
	
	@Value("${mah.server.url}")
	private String url;
	
	@Test
	public void testAccountLogin() {
		/*
		MAHConnectionAccountModelResultContainer ret = 
				service.accountLogin(MAHConnectionLogin.builder()
						.login(login)
						.password(password).build());
		*/
	}
	
	//@Test
	public void testSaveModuleInfo() {
		PluginInfoModelDtoWrapped getInfo = moduleService.getInfo(url);
		DtoWrapper ret = service.saveModuleInfo(getInfo.getContent());
	}
	
	@Test
	public void testisTokenValid() {
		//boolean tokenValid = service.isTokenValid("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiMTQiLCJleHAiOjE2Njc5OTIwMTEsImlzcyI6Imh0dHA6Ly9hcm0taW5zLTA4MDNyOjgwODAvbWljcm9zZXJ2aWNlcy9hZG1pbmlzdHJhdG9yIiwiYXVkIjoiaHR0cDovL2FybS1pbnMtMDgwM3I6ODA4MC8ifQ.mbIwsPKMRxjYQdXJPCtE6VIYKwR2friDPc1JzfqFQ3E");
		//assertTrue(tokenValid);
	}
}
