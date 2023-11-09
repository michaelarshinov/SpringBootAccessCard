package ru.michaelarshinovhome.Template;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ru.michaelarshinovhome.Template.dto.wrapped.ThemeDtoCollectionWrapped;
import ru.michaelarshinovhome.Template.service.ThemeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThemeDtoServiceTest {
	@Autowired
	ThemeService service;
	
	@Test
	public void test() {
		ThemeDtoCollectionWrapped ret = service.findById(1);
	}
}
