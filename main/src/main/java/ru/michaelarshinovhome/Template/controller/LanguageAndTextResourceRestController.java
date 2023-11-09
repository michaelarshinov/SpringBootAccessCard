package ru.michaelarshinovhome.Template.controller;

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
import ru.michaelarshinovhome.Template.dto.wrapped.LanguageDtoWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.TextResourceDtoWrapped;
import ru.michaelarshinovhome.Template.model.TextResource;
import ru.michaelarshinovhome.Template.service.LanguageService;
import ru.michaelarshinovhome.Template.service.TextResourceService;

@RestController
@Tag(name = "Localization", description = "Контроллер для работы с Локализациями.")
public class LanguageAndTextResourceRestController implements RestControllerSortable {
	@Autowired
	LanguageService languageService;
	
	@Autowired
	TextResourceService textResourceService;
	
	@Operation(summary = "Метод получения сведений о всех доступных языков.")
	@RequestMapping(value="/api/Localization/GetLanguage", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = LanguageDtoWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(languageService.findAll(), HttpStatus.OK);
	}
	
	@Operation(summary = "Метод получения сведений о всех текстовых ресурсах.", 
			description = "Получить сведения о Языках. Таблица называется TextResources.")
	@RequestMapping(value="/api/Localization/GetAutoriseTextResource", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = TextResourceDtoWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> findAllTextResources() {
				return new ResponseEntity<>(textResourceService.findAll(), HttpStatus.OK);
	}
	
	@Operation(summary = "Метод получения всех текстовых ресурсов. Постраничное чтение с возможностью сортировки.", 
			description = "Получение сведений о Языках. Таблица называется TextResources.")
	@RequestMapping(value="/api/Localization/GetAutoriseTextResource/{pageSize}/{pageNumber}", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = TextResourceDtoWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> findAllTextResources(
			@Parameter(name = "pageSize", required = false, 
			description = DESC_PAGE_SIZE)
		@PathVariable(required = false, name = "pageSize") Integer pageSize,
		@Parameter(name = "pageNumber", required = false, 
			description = DESC_PAGE_NUMBER)
		@PathVariable(required = false, name="pageNumber") Integer pageNumber,
		@Parameter(name = "fieldsToSortBy", required = false, 
		description = "Поля, по которым происходит сортировка. "
				+ "Например: resourceKey,textValue,modueId." + DESC_SORTING_FIELDS)
		@RequestParam(required = false, name = "fieldsToSortBy") String fieldsToSortBy,
		@Parameter(name = "orders", required = false, 
		description = DESC_SORTING_ORDER)
		@RequestParam(required = false, name = "orders") String orders) {
		int pageSizeVal = pageSize!=null ? pageSize : VAL_PAGE_SIZE;
		int pageNumberVal = pageNumber!=null ? pageNumber-1 : VAL_PAGE_NUMBER-1;
		Pageable pageable = getPageRequestWithSorting(pageNumberVal, pageSizeVal,
				fieldsToSortBy, orders, TextResource.class);
		return new ResponseEntity<>(textResourceService.findAll(pageable), HttpStatus.OK);
	}
}

