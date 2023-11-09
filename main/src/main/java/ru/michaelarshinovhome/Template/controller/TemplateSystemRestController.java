package ru.michaelarshinovhome.Template.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
import ru.michaelarshinovhome.Template.dao.OffsetBasedPageRequest;
import ru.michaelarshinovhome.Template.dto.TemplateSystemDto;
import ru.michaelarshinovhome.Template.dto.TemplateSystemDtoInput;
import ru.michaelarshinovhome.Template.dto.TemplateSystemDtoInputStatus;
import ru.michaelarshinovhome.Template.dto.wrapped.TemplateSystemDtoOneWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.TemplateSystemDtoWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.TemplateSystemMinimalDtoWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.TemplateSystemNameUUIDPairsDtoWrapped;
import ru.michaelarshinovhome.Template.model.TemplateSystem;
import ru.michaelarshinovhome.Template.service.TemplateSystemService;

@RestController
@Tag(name = "Template System", description = "Контроллер для работы с множествами СКУД.")
public class TemplateSystemRestController implements RestControllerSortable {
	private static final Logger logger = LoggerFactory.getLogger(TemplateSystemRestController.class);
	
	@Autowired
	TemplateSystemService templateSystemService;
	
	@Operation(summary = "Метод получения сведений о всех доступных СКУД. Содержит список Точек Доступа.")
	@RequestMapping(value="/api/TemplateSystem/GetAll", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = TemplateSystemDtoWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> findAll(
				@Parameter(name = "skip", required = false, description = DESC_SKIP_SIZE) 
				@RequestParam(required = false, name = "skip") Integer skip,
				@Parameter(name = "take", required = false, description = DESC_TAKE_SIZE) 
				@RequestParam(required = false, name = "take") Integer take) {
		int skipVal = skip != null ? skip : VAL_SKIP_SIZE;
		int takeVal = take != null ? take : VAL_TAKE_SIZE;
		Pageable pageable = new OffsetBasedPageRequest(skipVal, takeVal);
		return new ResponseEntity<>(templateSystemService.findAll(pageable), HttpStatus.OK);
	}
	
	@Operation(summary = "Метод получения сведений о всех доступных СКУД. Только Название, Марка, Версия, Логотип, статус и GUID.")
	@RequestMapping(value="/api/TemplateSystem/GetAllMinimal", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = TemplateSystemMinimalDtoWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> findAllMinimal() {
		return new ResponseEntity<>(templateSystemService.findAllMinimal(), HttpStatus.OK);
	}
	
	@Operation(summary = "Метод получения сведений о всех доступных СКУД в формате пар Название/Идентификатор.")
	@RequestMapping(value="/api/TemplateSystem/GetAllNameUUIDPairs", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation =  TemplateSystemNameUUIDPairsDtoWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> findAllNameUUIDPairs() {
		return new ResponseEntity<>(templateSystemService.findAllNameUUIDPairs(), HttpStatus.OK);
	}
	
	/*@Operation(summary = "Метод получения сведений о всех доступных СКУД. СКУД содержит список Точек Доступа.")
	@RequestMapping(value="/api/TemplateSystem/GetAll/PointsView", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = TemplateSystemDtoWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> findAllPointsView() {
		return new ResponseEntity<>(templateSystemService.findAllPointsView(), HttpStatus.OK);
	}*/
	
	/*@Operation(summary = "Метод получения сведений о всех доступных СКУД. СКУД содержит список Зон Доступа.")
	@RequestMapping(value="/api/TemplateSystem/GetAll/ZonesView", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = TemplateSystemZonesViewDto.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> findAllZonesView() {
		return new ResponseEntity<>(templateSystemService.findAllZonesView(), HttpStatus.OK);
	}*/
	
	@Operation(summary = "Метод получения сведений об определенном СКУД.")
	@RequestMapping(value="/api/TemplateSystem/Get/{templateSystemId}", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = TemplateSystemDtoOneWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> findById(@Parameter(name = "templateSystemId", required = true, 
			description = "Идентификатор СКУД.")
		@PathVariable(required = true, name = "templateSystemId") UUID templateSystemId) {
		return new ResponseEntity<>(templateSystemService.findById(templateSystemId), HttpStatus.OK);
	}
	
	/*@Operation(summary = "Метод получения сведений об определенном СКУД. СКУД содержит список Точек Доступа.")
	@RequestMapping(value="/api/TemplateSystem/Get/PointsView/{templateSystemId}", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = TemplateSystemPointsViewDtoOneWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> findByIdPointsView(@Parameter(name = "templateSystemId", required = true, 
			description = "Идентификатор СКУД.")
		@PathVariable(required = true, name = "templateSystemId") UUID templateSystemId) {
		return new ResponseEntity<>(templateSystemService.findByIdPointsView(templateSystemId), HttpStatus.OK);
	}*/
	
	/*@Operation(summary = "Метод получения сведений об определенном СКУД. СКУД содержит список Зон Доступа.")
	@RequestMapping(value="/api/TemplateSystem/Get/ZonesView/{templateSystemId}", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = TemplateSystemZonesViewDtoOneWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> findByIdZonesView(@Parameter(name = "templateSystemId", required = true, 
			description = "Идентификатор СКУД.")
		@PathVariable(required = true, name = "templateSystemId") UUID templateSystemId) {
		return new ResponseEntity<>(templateSystemService.findByIdZonesView(templateSystemId), HttpStatus.OK);
	}*/
	
	@Operation (summary = "Получить сведения о количестве СКУД.")
	@RequestMapping(value="/api/TemplateSystem/Count", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Long.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> count() {
		return new ResponseEntity<>(templateSystemService.count(), HttpStatus.OK);
	}
	
	@Operation(summary = "Метод получения сведений о всех доступных СКУД. Постраничное чтение с возможностью сортировки.", 
			description = "Получение сведений о СКУД.")
	@RequestMapping(value="/api/TemplateSystem/GetAll/{pageSize}/{pageNumber}", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = TemplateSystemDtoWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> findAllPageableSortable(
			@Parameter(name = "pageSize", required = false, 
			description = DESC_PAGE_SIZE)
		@PathVariable(required = false, name = "pageSize") Integer pageSize,
		@Parameter(name = "pageNumber", required = false, 
			description = DESC_PAGE_NUMBER)
		@PathVariable(required = false, name="pageNumber") Integer pageNumber,
		@Parameter(name = "fieldsToSortBy", required = false, 
		description = "Поля, по которым происходит сортировка. "
				+ "Например: name,version,enabled." + DESC_SORTING_FIELDS)
		@RequestParam(required = false, name = "fieldsToSortBy") String fieldsToSortBy,
		@Parameter(name = "orders", required = false, 
		description = DESC_SORTING_ORDER)
		@RequestParam(required = false, name = "orders") String orders) {
		int pageSizeVal = pageSize!=null ? pageSize : VAL_PAGE_SIZE;
		int pageNumberVal = pageNumber!=null ? pageNumber-1 : VAL_PAGE_NUMBER-1;
		Pageable pageable = getPageRequestWithSorting(pageNumberVal, pageSizeVal,
				fieldsToSortBy, orders, TemplateSystem.class);
		return new ResponseEntity<>(templateSystemService.findAll(pageable), HttpStatus.OK);
	}

	@PostMapping(value = "/api/TemplateSystem/Create", 
			consumes = "application/json", produces = "application/json")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Успешно создано", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = TemplateSystemDtoOneWrapped.class)))),
	        @ApiResponse(responseCode = "400", description = "Плохой запрос")})
	@Operation(summary = "Метод добавления Зоны Доступа в СКУД.", 
			description = "Регистрация Зоны Доступа в СКУД.")
	public ResponseEntity<?> createACS(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(content = 
					@Content(schema=@Schema(implementation = TemplateSystemDtoInput.class)))
			@RequestBody TemplateSystemDtoInput templateSystemObject) {
		TemplateSystemDto created = null;
		try {
			created = templateSystemService.create(templateSystemObject);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<>(new TemplateSystemDtoOneWrapped(created), 
					created == null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/api/TemplateSystem/Status", 
			consumes = "application/json", produces = "application/json", method=RequestMethod.PATCH)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно изменено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = TemplateSystemDtoOneWrapped.class)))),
	        @ApiResponse(responseCode = "400", description = "Плохой запрос")})
	@Operation(summary = "Метод изменения статуса СКУД.")
	public ResponseEntity<?> setStatus(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(content = 
					@Content(schema=@Schema(implementation = TemplateSystemDtoInputStatus.class)))
			@RequestBody TemplateSystemDtoInputStatus templateSystemObject) {
		TemplateSystemDto created = null;
		try {
			created = templateSystemService.changeStatus(templateSystemObject);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<>(new TemplateSystemDtoOneWrapped(created), 
					created == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
	}
}
