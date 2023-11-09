package ru.michaelarshinovhome.Template.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.michaelarshinovhome.Template.dto.TemplateSystemDto;
import ru.michaelarshinovhome.Template.dto.TemplateSystemDtoInput;
import ru.michaelarshinovhome.Template.dto.AccessPointDto;
import ru.michaelarshinovhome.Template.dto.AccessPointDtoInput;
import ru.michaelarshinovhome.Template.dto.wrapped.TemplateSystemDtoOneWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.AccessPointDtoOneWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.AccessPointDtoWrapped;
import ru.michaelarshinovhome.Template.service.AccessPointService;

@RestController
@Tag(name = "Access Point", description = "Контроллер для работы с множествами Точек Доступа СКУД.")
public class AccessPointRestController {
	private static final Logger logger = LoggerFactory.getLogger(AccessPointRestController.class);
	
	@Autowired
	AccessPointService accessPointService;
	
	@Operation (summary = "Получить сведения о количестве Точек Доступа всех СКУД.")
	@RequestMapping(value="/api/AccessPoint/Count", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Long.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> count() {
		return new ResponseEntity<>(accessPointService.count(), HttpStatus.OK);
	}
	
	@Operation (summary = "Получить сведения о количестве Точек Доступа определенной СКУД.")
	@RequestMapping(value="/api/AccessPoint/Count/{templateSystemId}", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Long.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> countByACSId(@Parameter(name = "templateSystemId", required = true, 
			description = "Идентификатор СКУД.")
		@PathVariable(required = true, name = "templateSystemId") UUID templateSystemId) {
		return new ResponseEntity<>(accessPointService.countBytemplateSystem(templateSystemId), HttpStatus.OK);
	}
	
	@Operation(summary = "Метод получения сведений об определенной Точке Доступа.")
	@RequestMapping(value="/api/AccessPoint/Get/{accessPointId}", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = AccessPointDtoOneWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> findById(@Parameter(name = "accessPointId", required = true, 
			description = "Идентификатор Точки Доступа.")
		@PathVariable(required = true, name = "accessPointId") UUID accessPointId) {
		return new ResponseEntity<>(accessPointService.findById(accessPointId), HttpStatus.OK);
	}
	
	@Operation(summary = "Метод получения сведений о всех Точках Доступа определенного СКУД.")
	@RequestMapping(value="/api/AccessPoint/GetAll/{templateSystemId}", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = AccessPointDtoWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> findAllByACSId(@Parameter(name = "templateSystemId", required = true, 
			description = "Идентификатор СКУД.")
		@PathVariable(required = true, name = "templateSystemId") UUID templateSystemId) {
		return new ResponseEntity<>(accessPointService.findAllbyACSId(templateSystemId), HttpStatus.OK);
	}
	
	@PostMapping(value = "/api/AccessPoint/Create", 
			consumes = "application/json", produces = "application/json")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Успешно создано", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = AccessPointDtoOneWrapped.class)))),
	        @ApiResponse(responseCode = "400", description = "Плохой запрос")})
	@Operation(summary = "Метод добавления Точки Доступа в СКУД.", 
			description = "Регистрация Точки Доступа в СКУД.")
	public ResponseEntity<?> createACS(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(content = 
					@Content(schema=@Schema(implementation = AccessPointDtoInput.class)))
			@RequestBody AccessPointDtoInput accessPointObject) {
		AccessPointDto created = null;
		try {
			created = accessPointService.create(accessPointObject);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<>(new AccessPointDtoOneWrapped(created), 
					created == null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED);
	}
}
