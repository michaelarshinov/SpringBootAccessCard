package ru.michaelarshinovhome.Template;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ru.michaelarshinovhome.Template.dto.wrapped.PluginInfoModelDtoWrapped;
import ru.michaelarshinovhome.Template.service.ModuleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModuleServiceTest {
	@Autowired
	ModuleService service;
	
	@Test
	public void test() {
		PluginInfoModelDtoWrapped ret = service.getInfo("http://123");
	}
}
