package ru.michaelarshinovhome.Template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.michaelarshinovhome.Template.dto.MAHConnectionAccountModelResultContainer;
import ru.michaelarshinovhome.Template.dto.MAHConnectionLogin;
import ru.michaelarshinovhome.Template.service.MAHConnectionService;

@RestController
@Tag(name = "Authorization", description = "Контроллер для работы с Мастер Системой")
public class AuthorizationRestController {
	@Autowired
	MAHConnectionService mahConnectionService;
	
	@Value("${mah.server.login}")
	private String login;

	@Value("${mah.server.password}")
	private String password;

	@Operation(summary = "Аутентификация в мастер системе (МС).")
	@RequestMapping(value="/api/Authorization/LoginIntoMS", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = VCRConnectionAccountModelResultContainer.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> loginIntoMS() {
		return new ResponseEntity<>(mahConnectionService.accountLogin(MAHConnectionLogin.builder()
				.login(login).password(password).build()), HttpStatus.OK);
	}
}
