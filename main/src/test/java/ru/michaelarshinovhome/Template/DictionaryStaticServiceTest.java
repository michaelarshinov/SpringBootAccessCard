package ru.michaelarshinovhome.Template;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ru.michaelarshinovhome.Template.dto.wrapped.DictionaryStaticDtoWrapped;
import ru.michaelarshinovhome.Template.service.DictionaryStaticService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DictionaryStaticServiceTest {
	
	@Autowired
	DictionaryStaticService service;
	
	@Test
	public void testGetByParentId() {// 1  2  5  11  14  25  29  33
		DictionaryStaticDtoWrapped wrapped = service.findAllByParentId(33);
	}
}
