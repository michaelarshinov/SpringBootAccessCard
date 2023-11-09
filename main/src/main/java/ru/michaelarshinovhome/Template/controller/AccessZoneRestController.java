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
import ru.michaelarshinovhome.Template.dto.AttendanceObjectDto;
import ru.michaelarshinovhome.Template.dto.AttendanceObjectDtoInput;
import ru.michaelarshinovhome.Template.dto.wrapped.AttendanceObjectDtoOneWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.AttendanceObjectDtoWrapped;
import ru.michaelarshinovhome.Template.model.AccessZone;
import ru.michaelarshinovhome.Template.service.AccessZoneService;
import ru.michaelarshinovhome.Template.service.AttendanceObjectService;

@RestController
@Tag(name = "Access Zone", description = "Контроллер для работы с множествами Зон Доступа СКУД.")
public class AccessZoneRestController implements RestControllerSortable {
	private static final Logger logger = LoggerFactory.getLogger(AccessZoneRestController.class);
	/*
	@Autowired
	AccessZoneService accessZoneService; 
	
	@Autowired
	AttendanceObjectService attendanceObjectService; 
	
	@Operation(summary = "Метод получения сведений о всех доступных Зон Доступа всех СКУД.")
	@RequestMapping(value="/api/AccessZone/GetAll", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = AccessZoneWithPointsDtoWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(accessZoneService.findAll(), HttpStatus.OK);
	}
	
	@Operation (summary = "Получить сведения о количестве Зон Доступа всех СКУД.")
	@RequestMapping(value="/api/AccessZone/Count", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Long.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> count() {
		return new ResponseEntity<>(accessZoneService.count(), HttpStatus.OK);
	}
	
	@Operation (summary = "Получить сведения о количестве Зон Доступа определенной СКУД.")
	@RequestMapping(value="/api/AccessZone/Count/{accessControlSystemId}", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Long.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> count(@Parameter(name = "accessControlSystemId", required = true, 
			description = "Идентификатор СКУД.")
		@PathVariable(required = true, name = "accessControlSystemId") UUID accessControlSystemId) {
		return new ResponseEntity<>(accessZoneService.countByAcsId(accessControlSystemId), HttpStatus.OK);
	}
	
	@Operation(summary = "Метод получения сведений о всех доступных Зон Доступа всех СКУД. Постраничное чтение с возможностью сортировки.", 
			description = "Получение сведений о Зонах Доступа всех СКУД.")
	@RequestMapping(value="/api/AccessZone/GetAll/{pageSize}/{pageNumber}", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = AccessZoneWithPointsDtoWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> findAllPageableSortable(
			@Parameter(name = "pageSize", required = false, 
			description = DESC_PAGE_SIZE)
		@PathVariable(required = false, name = "pageSize") Integer pageSize,
		@Parameter(name = "pageNumber", required = false, 
			description =DESC_PAGE_NUMBER)
		@PathVariable(required = false, name="pageNumber") Integer pageNumber,
		@Parameter(name = "fieldsToSortBy", required = false, 
		description = "Поля, по которым происходит сортировка. "
				+ "Например: name,acsId,id. Поля перечисляются через запятую. "
				+  DESC_SORTING_FIELDS)
		@RequestParam(required = false, name = "fieldsToSortBy") String fieldsToSortBy,
		@Parameter(name = "orders", required = false, 
		description = DESC_SORTING_ORDER)
		@RequestParam(required = false, name = "orders") String orders) {
		int pageSizeVal = pageSize!=null ? pageSize : VAL_PAGE_SIZE;
		int pageNumberVal = pageNumber!=null ? pageNumber-1 : VAL_PAGE_NUMBER-1;
		Pageable pageable = getPageRequestWithSorting(pageNumberVal, pageSizeVal,
				fieldsToSortBy, orders, AccessZone.class);
		return new ResponseEntity<>(accessZoneService.findAll(pageable), HttpStatus.OK);
	}
	
	@Operation(summary = "Метод получения сведений о всех доступных Зон Доступа определенной СКУД. Постраничное чтение с возможностью сортировки.", 
			description = "Получение сведений о Зонах Доступа определенной СКУД.")
	@RequestMapping(value="/api/AccessZone/GetAll/{accessControlSystemId}/{pageSize}/{pageNumber}", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = AccessZoneWithPointsDtoWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> findAllPageableSortableByACSId(
			@Parameter(name = "accessControlSystemId", required = true, 
			description = "Идентификатор СКУД.")
		@PathVariable(required = true, name = "accessControlSystemId") UUID accessControlSystemId,
			@Parameter(name = "pageSize", required = false, 
			description = DESC_PAGE_SIZE)
		@PathVariable(required = false, name = "pageSize") Integer pageSize,
		@Parameter(name = "pageNumber", required = false, 
			description = DESC_PAGE_NUMBER)
		@PathVariable(required = false, name="pageNumber") Integer pageNumber,
		@Parameter(name = "fieldsToSortBy", required = false, 
		description = "Поля, по которым происходит сортировка. "
				+ "Например: name,acsId,id."
				+ DESC_SORTING_FIELDS)
		@RequestParam(required = false, name = "fieldsToSortBy") String fieldsToSortBy,
		@Parameter(name = "orders", required = false, 
		description = DESC_SORTING_ORDER)
		@RequestParam(required = false, name = "orders") String orders) {
		int pageSizeVal = pageSize!=null ? pageSize : VAL_PAGE_SIZE;
		int pageNumberVal = pageNumber!=null ? pageNumber-1 : VAL_PAGE_NUMBER-1;
		Pageable pageable = getPageRequestWithSorting(pageNumberVal, pageSizeVal,
				fieldsToSortBy, orders, AccessZone.class);
		return new ResponseEntity<>(accessZoneService.findAllbyACSId(accessControlSystemId, pageable), HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/api/AccessZone/Create", 
			consumes = "application/json", produces = "application/json")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = AccessZoneWithPointsDtoOneWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	@Operation(summary = "Метод добавления Зоны Доступа в СКУД.", 
			description = "Регистрация Зоны Доступа в СКУД в системе.")
	public ResponseEntity<?> createAccessZone(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(content = 
					@Content(schema=@Schema(implementation =  AccessZoneDtoInput.class)))
			@RequestBody AccessZoneDtoInput accessZoneDtoInput) {
		AccessZoneDto created = null;
		try {
			created = accessZoneService.create(accessZoneDtoInput);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<>(new AccessZoneDtoOneWrapped(created), 
					created == null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED);
	}
	
	@Operation(summary = "Метод добавления Объекта посещения в определенную Зону Доступа определенной СКУД.")
	@RequestMapping(value="/api/AccessZone/AttendanceObject/Create", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = AttendanceObjectDtoWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> createAttendanceObject(@io.swagger.v3.oas.annotations.parameters.RequestBody(content = 
			@Content(schema=@Schema(implementation =  AttendanceObjectDtoInput.class)))
	@RequestBody AttendanceObjectDtoInput attendanceObjectDtoInput) {
		AttendanceObjectDto created = null;
		try {
			created = attendanceObjectService.addAttendanceObject(attendanceObjectDtoInput);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<>(new AttendanceObjectDtoOneWrapped(created), 
				created == null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED);
	}
	
	@Operation(summary = "Метод получения сведений об определенной Зоне Доступа СКУД.")
	@RequestMapping(value="/api/AccessZone/Get/{accessZoneId}", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = AccessZoneWithPointsDtoOneWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> findById(@Parameter(name = "accessZoneId", required = true, 
			description = "Идентификатор Зоны Доступа СКУД.")
		@PathVariable(required = true, name = "accessZoneId") UUID accessZoneId) {
		return new ResponseEntity<>(accessZoneService.findById(accessZoneId), HttpStatus.OK);
	}
	*/
}
