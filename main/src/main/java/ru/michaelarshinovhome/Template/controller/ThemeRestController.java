package ru.michaelarshinovhome.Template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
import ru.michaelarshinovhome.Template.dto.wrapped.ThemeDtoCollectionWrapped;
import ru.michaelarshinovhome.Template.service.ThemeService;

@RestController
@Tag(name = "Themes", description = "Контроллер для работы с Темами")
public class ThemeRestController {
	@Autowired
	ThemeService service;
	@Operation(summary = "Получение тем модуля.")
	@RequestMapping(value="/api/Themes/GetThemes/{themeId}", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = ThemeDtoCollectionWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> findAll(@PathVariable(value="themeId") Integer themeId) {
		if (themeId != null) {
			ThemeDtoCollectionWrapped ret = service.findById(themeId);
			if (ret != null) {
				return new ResponseEntity<>(ret, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
}
