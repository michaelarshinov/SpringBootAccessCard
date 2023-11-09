package ru.michaelarshinovhome.Template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.michaelarshinovhome.Template.dto.MAHConnectionAccountModelResultContainer;
import ru.michaelarshinovhome.Template.dto.MAHConnectionLogin;
import ru.michaelarshinovhome.Template.dto.wrapped.PluginInfoModelDtoWrapped;
import ru.michaelarshinovhome.Template.service.ModuleService;
import ru.michaelarshinovhome.Template.service.MAHConnectionService;

@RestController
@Tag(name = "Module", description = "Контроллер для работы с Модулем.")
public class ModuleRestController {
	@Autowired
	ModuleService moduleService;
	
	@Value("${mah.server.login}")
	private String MAHlogin;

	@Value("${mah.server.password}")
	private String password;
	
	@Value("${module.server.url}")
	private String moduleServerUrl;
	
	@Autowired 
	MAHConnectionService mahConnectionService;
	
	@Operation(summary = "Получение сведений о модуле.")
	@RequestMapping(value="/api/Module/GetInfoModule", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = PluginInfoModelDtoWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> getInfoModule(@Parameter(description = "URL сервера модуля с портом. Например http://arm-ins-0811r:8093/",
		    name =  "url",
		    example = "вставьте значение module.server.url из application.properties",
		    required = true)
		@RequestParam(value="url") String url) {
		return new ResponseEntity<>(moduleService.getInfo(url), HttpStatus.OK);
	}

	@Operation(summary = "Подключение модуля к МС.")
	@RequestMapping(value="/api/Module/PlugInModule", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = PluginInfoModelDtoWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> plugInModule(@Parameter(description = "Адрес подключаемого модуля",
		    name =  "url",
			example = "например, http://arm-ins-0811r:8100/",
		    required = true) @RequestParam(value="url") String url) {
		MAHConnectionAccountModelResultContainer login = mahConnectionService.accountLogin(MAHConnectionLogin.builder()
				.login(MAHlogin).password(password).build());
		if (!login.isSuccess()) {
			return new ResponseEntity<>(new PluginInfoModelDtoWrapped(false, login.getMessage()), HttpStatus.UNAUTHORIZED);
		}
		PluginInfoModelDtoWrapped getInfo = moduleService.getInfo(url);
		if (!getInfo.isSuccess()) {
			return new ResponseEntity<>(new PluginInfoModelDtoWrapped(false, getInfo.getMessage()), HttpStatus.NOT_IMPLEMENTED);			
		}
		return new ResponseEntity<>(mahConnectionService.saveModuleInfo(getInfo.getContent()), HttpStatus.OK);
	}

}
