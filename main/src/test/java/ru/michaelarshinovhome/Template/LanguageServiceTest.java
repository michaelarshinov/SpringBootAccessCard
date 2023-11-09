package ru.michaelarshinovhome.Template;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ru.michaelarshinovhome.Template.dao.LanguageRepository;
import ru.michaelarshinovhome.Template.dto.wrapped.LanguageDtoWrapped;
import ru.michaelarshinovhome.Template.service.impl.LanguageServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LanguageServiceTest {
	@Autowired
	LanguageServiceImpl service;
	@Autowired
	LanguageRepository languageRepository;
	
	@Test
	public void textFindAll() {
		LanguageDtoWrapped list = service.findAll();
	}
}
