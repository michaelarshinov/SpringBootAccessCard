package ru.michaelarshinovhome.Template.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import ru.michaelarshinovhome.Template.dto.wrapped.DictionaryStaticDtoWrapped;
import ru.michaelarshinovhome.Template.service.DictionaryStaticService;

@RestController
@Tag(name = "DictionaryStatic", description = "Контроллер для работы со Справочниками")
public class DictionaryStaticRestController {

	@Autowired
	DictionaryStaticService dictionaryStaticService;
	
	@Operation(summary = "Получение статичного справочника модуля.")
	@RequestMapping(value="/api/DictionaryStatic/GetDictionaryStatic", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = DictionaryStaticDtoWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(dictionaryStaticService.findAll(), HttpStatus.OK);
	}
	
	@Operation(summary = "Получение списка значений для конкретного узла.")
	@RequestMapping(value="/api/DictionaryStatic/GetDictionaryStaticNode", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = DictionaryStaticDtoWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> findByNode(@Parameter(description = "Id узла справочника: 3 - Тип ИДД ",
		    name =  "nodeId",
		    example = "2",
		    required = true)
		@RequestParam(value="nodeId") Integer nodeId) {
		return new ResponseEntity<>(dictionaryStaticService.findAllByParentId(nodeId), HttpStatus.OK);
	}
}
