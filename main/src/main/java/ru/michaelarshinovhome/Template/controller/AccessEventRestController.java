package ru.michaelarshinovhome.Template.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
import ru.michaelarshinovhome.Template.dto.wrapped.AccessControlSystemDtoWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.AccessEventDtoWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.AccessIdTypeDtoWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.RFIDCardDtoWrapped;
import ru.michaelarshinovhome.Template.model.AccessControlSystem;
import ru.michaelarshinovhome.Template.model.AccessEvent;
import ru.michaelarshinovhome.Template.service.AccessEventService;

@RestController
@Tag(name = "Access Event", description = "Контроллер для работы с множествами Событий Доступа.")
public class AccessEventRestController implements RestControllerSortable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3123063900420027637L;

	private static final Logger logger = LoggerFactory.getLogger(AccessEventRestController.class);
	
	@Autowired
	AccessEventService accessEventService;
	
	@Operation(summary = "Метод получения сведений о Типах ИДД. Наименования Типов Идентификаторов Доступа возвращаются в переводе на нужную локализацию.")
	@RequestMapping(value="/api/AccessEvent/GetAccessIdTypeDictionary/{language}", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = AccessIdTypeDtoWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> getAccessIdTypeDictionary(@Parameter(name = "language", required = true, 
			description = "Language. Например 1 - русский, 2 - english")
		@PathVariable(required = true, name = "language") Integer language) {
		return new ResponseEntity<>(accessEventService.getAccessIdTypes(language), HttpStatus.OK);
	}
	
	@Operation (summary = "Получить сведения о количестве Событий Доступа.")
	@RequestMapping(value="/api/AccessEvent/Count", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Long.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> count() {
		return new ResponseEntity<>(accessEventService.count(), HttpStatus.OK);
	}
	
	@Operation(summary = "Метод получения сведений о всех Событиях Доступа. Постраничное чтение с возможностью сортировки.", 
			description = "Получение сведений о Событиях Доступа.")
	@RequestMapping(value="/api/AccessEvent/GetAll/{pageSize}/{pageNumber}", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = AccessEventDtoWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> findAllPageableSortable(
		@Parameter(description = "код локализации, например 1 - русский, 2 - english. Значение по умолнию - 1", name =  "languageId", example = "2", required = false) 
		@RequestParam(value="languageId") Integer languageId,	
		@Parameter(name = "pageSize", required = false, description = DESC_PAGE_SIZE)
		@PathVariable(required = false, name = "pageSize") Integer pageSize,
		@Parameter(name = "pageNumber", required = false, 
			description = DESC_PAGE_NUMBER)
		@PathVariable(required = false, name="pageNumber") Integer pageNumber,
		@Parameter(name = "fieldsToSortBy", required = false, 
		description = "Поля, по которым происходит сортировка. (id, accessIdType, accessId, acsId, date, accessPointId)"
				+ "Например: name,version,enabled." + DESC_SORTING_FIELDS)
		@RequestParam(required = false, name = "fieldsToSortBy") String fieldsToSortBy,
		@Parameter(name = "orders", required = false, 
		description = DESC_SORTING_ORDER)
		@RequestParam(required = false, name = "orders") String orders) {
		int pageSizeVal = pageSize!=null ? pageSize : VAL_PAGE_SIZE;
		int pageNumberVal = pageNumber!=null ? pageNumber-1 : VAL_PAGE_NUMBER-1;
		Pageable pageable = getPageRequestWithSorting(pageNumberVal, pageSizeVal,
				fieldsToSortBy, orders, AccessEvent.class);
		return new ResponseEntity<>(accessEventService.findAll(pageable, languageId), HttpStatus.OK);
	}
	
	@Operation(summary = "Метод получения сведений о Событиях Доступа.")
	@RequestMapping(value = "/api/AccessEvent/GetAll", method = RequestMethod.GET)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Успешно найдено", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AccessEventDtoWrapped.class)))),
			@ApiResponse(responseCode = "404", description = "Не найдено") })
	public ResponseEntity<?> findAllPageable(
			@Parameter(description = "код локализации, например 1 - русский, 2 - english. Значение по умолнию - 1", name =  "languageId", example = "2", required = false) 
			@RequestParam(value="languageId") Integer languageId,	
			@Parameter(name = "skip", required = false, description = DESC_SKIP_SIZE) 
			@RequestParam(required = false, name = "skip") Integer skip,
			@Parameter(name = "take", required = false, description = DESC_TAKE_SIZE) 
			@RequestParam(required = false, name = "take") Integer take) {
		int skipVal = skip != null ? skip : VAL_SKIP_SIZE;
		int takeVal = take != null ? take : VAL_TAKE_SIZE;
		Pageable pageable = new OffsetBasedPageRequest(skipVal, takeVal);
		return new ResponseEntity<>(accessEventService.findAll(pageable, languageId), HttpStatus.OK);
	}
	
}
