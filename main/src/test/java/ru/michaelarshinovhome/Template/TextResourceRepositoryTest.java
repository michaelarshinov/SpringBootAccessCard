package ru.michaelarshinovhome.Template;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ru.michaelarshinovhome.Template.dto.wrapped.TextResourceDtoWrapped;
import ru.michaelarshinovhome.Template.service.TextResourceService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TextResourceRepositoryTest {
	@Autowired
	public TextResourceService service;
	
	@Test
	public void textFindAll() {
		TextResourceDtoWrapped list = service.findAll();
		
	}
}
