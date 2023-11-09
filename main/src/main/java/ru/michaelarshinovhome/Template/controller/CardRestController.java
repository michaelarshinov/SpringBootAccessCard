package ru.michaelarshinovhome.Template.controller;

import java.io.Serializable;
import java.util.List;
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
import ru.michaelarshinovhome.Template.dto.RFIDCardDto;
import ru.michaelarshinovhome.Template.dto.RFIDCardDtoInput;
import ru.michaelarshinovhome.Template.dto.wrapped.TemplateSystemDtoOneWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.RFIDCardDtoOneWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.RFIDCardDtoWrapped;
import ru.michaelarshinovhome.Template.service.CardService;

@RestController
@Tag(name = "Card", description = "Контроллер для работы с множествами RFID Карт.")
public class CardRestController implements RestControllerSortable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2505174107513447273L;

	private static final Logger logger = LoggerFactory.getLogger(CardRestController.class);
	
	@Autowired
	CardService cardService;
	
	@PostMapping(value = "/api/Card/Create", 
			consumes = "application/json", produces = "application/json")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Успешно создано", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = RFIDCardDtoOneWrapped.class)))),
	        @ApiResponse(responseCode = "400", description = "Плохой запрос")})
	@Operation(summary = "Метод создания RFID Карты.")
	public ResponseEntity<?> createCard(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(content = 
					@Content(schema=@Schema(implementation = RFIDCardDtoInput.class)))
			@RequestBody RFIDCardDtoInput cardObject) {
		RFIDCardDto created = null;
		try {
			created = cardService.create(cardObject);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<>(new RFIDCardDtoOneWrapped(created), 
					created == null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED);
	}
	
	@Operation(summary = "Метод получения сведений о Картах RFID.")
	@RequestMapping(value = "/api/Card/GetCardList", method = RequestMethod.GET)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Успешно найдено", content = @Content(array = @ArraySchema(schema = @Schema(implementation = RFIDCardDtoWrapped.class)))),
			@ApiResponse(responseCode = "404", description = "Не найдено") })
	public ResponseEntity<?> getCardList(
			@Parameter(name = "skip", required = false, description = DESC_SKIP_SIZE) 
			@RequestParam(required = false, name = "skip") Integer skip,
			@Parameter(name = "take", required = false, description = DESC_TAKE_SIZE) 
			@RequestParam(required = false, name = "take") Integer take) {
		int skipVal = skip != null ? skip : VAL_SKIP_SIZE;
		int takeVal = take != null ? take : VAL_TAKE_SIZE;
		Pageable pageable = new OffsetBasedPageRequest(skipVal, takeVal);
		return new ResponseEntity<>(cardService.findAll(pageable), HttpStatus.OK);
	}
	
	@Operation(summary = "Метод получения сведений о Картах RFID по СКУД id.")
	@RequestMapping(value = "/api/Card/GetCardListBytemplateSystemId", method = RequestMethod.GET)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Успешно найдено", content = @Content(array = @ArraySchema(schema = @Schema(implementation = RFIDCardDtoWrapped.class)))),
			@ApiResponse(responseCode = "404", description = "Не найдено") })
	public ResponseEntity<?> getCardListBytemplateSystemId(
			@Parameter(name = "templateSystemId", required = true, 
			description = "Идентификатор СКУД.")
			@RequestParam(required = true, name = "templateSystemId") UUID templateSystemId,
			@Parameter(name = "skip", required = false, description = DESC_SKIP_SIZE) 
			@RequestParam(required = false, name = "skip") Integer skip,
			@Parameter(name = "take", required = false, description = DESC_TAKE_SIZE) 
			@RequestParam(required = false, name = "take") Integer take) {
		int skipVal = skip != null ? skip : VAL_SKIP_SIZE;
		int takeVal = take != null ? take : VAL_TAKE_SIZE;
		Pageable pageable = new OffsetBasedPageRequest(skipVal, takeVal);
		return new ResponseEntity<>(cardService.findAllBytemplateSystemId(templateSystemId, pageable), HttpStatus.OK);
	}
	
	@Operation(summary = "Метод получения сведений Карте RFID по id.")
	@RequestMapping(value = "/api/Card/GetCardById", method = RequestMethod.GET)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Успешно найдено", content = @Content(array = @ArraySchema(schema = @Schema(implementation = RFIDCardDtoOneWrapped.class)))),
			@ApiResponse(responseCode = "404", description = "Не найдено") })
	public ResponseEntity<?> getCardById(
			@Parameter(name = "cardId", required = true, description = "Идентификатор Карты RFID.")
			@RequestParam(required = true, name = "cardId") UUID cardId){
		return new ResponseEntity<>(cardService.findById(cardId), HttpStatus.OK);
	}
	
	@Operation(summary = "Метод получения сведений о Картах RFID по СКУД Id и статусу.")
	@RequestMapping(value = "/api/Card/GetCardListBytemplateSystemIdAndStatus", method = RequestMethod.GET)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Успешно найдено", content = @Content(array = @ArraySchema(schema = @Schema(implementation = RFIDCardDtoWrapped.class)))),
			@ApiResponse(responseCode = "404", description = "Не найдено") })
	public ResponseEntity<?> getCardListBytemplateSystemIdAndStatus(
			@Parameter(name = "templateSystemId", required = true, 
			description = "Идентификатор СКУД.")
			@RequestParam(required = true, name = "templateSystemId") UUID templateSystemId,
			@Parameter(name = "status", required = true, 
			description = "Статус карты RFID.")
			@RequestParam(required = true, name = "status") Short status,
			@Parameter(name = "skip", required = false, description = DESC_SKIP_SIZE) 
			@RequestParam(required = false, name = "skip") Integer skip,
			@Parameter(name = "take", required = false, description = DESC_TAKE_SIZE) 
			@RequestParam(required = false, name = "take") Integer take) {
		int skipVal = skip != null ? skip : VAL_SKIP_SIZE;
		int takeVal = take != null ? take : VAL_TAKE_SIZE;
		Pageable pageable = new OffsetBasedPageRequest(skipVal, takeVal);
		return new ResponseEntity<>(cardService.findAllBytemplateSystemIdAndStatus(templateSystemId,status,pageable), HttpStatus.OK);
	}
	
	@Operation(summary = "Метод получения сведений о Картах RFID по нескольким СКУД Id и статусу.")
	@RequestMapping(value = "/api/Card/GetCardListBytemplateSystemIdsAndStatus", method = RequestMethod.GET)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Успешно найдено", content = @Content(array = @ArraySchema(schema = @Schema(implementation = RFIDCardDtoWrapped.class)))),
			@ApiResponse(responseCode = "404", description = "Не найдено") })
	public ResponseEntity<?> getCardListBytemplateSystemIdsAndStatus(
			@Parameter(name = "templateSystemIds", required = true,
			description = "Идентификаторы СКУД. Значения перечисляются через запятую.")
			@RequestParam(required = false, name = "templateSystemIds") List<UUID> templateSystemIds,
			@Parameter(name = "status", required = true, 
			description = "Статус карты RFID.")
			@RequestParam(required = true, name = "status") Short status,
			@Parameter(name = "skip", required = false, description = DESC_SKIP_SIZE) 
			@RequestParam(required = false, name = "skip") Integer skip,
			@Parameter(name = "take", required = false, description = DESC_TAKE_SIZE) 
			@RequestParam(required = false, name = "take") Integer take) {
		int skipVal = skip != null ? skip : VAL_SKIP_SIZE;
		int takeVal = take != null ? take : VAL_TAKE_SIZE;
		Pageable pageable = new OffsetBasedPageRequest(skipVal, takeVal);
		return new ResponseEntity<>(cardService.findAllBytemplateSystemIdsAndStatus(templateSystemIds,status,pageable), HttpStatus.OK);
	}
	
	@Operation(summary = "Поиск RFID карт по Читаемому номеру карты, Заводскому номер карты, Номеру карты без преобразования, владельцу.")
	@RequestMapping(value = "/api/Card/SearchCardList", method = RequestMethod.GET)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Успешно найдено", content = @Content(array = @ArraySchema(schema = @Schema(implementation = RFIDCardDtoWrapped.class)))),
			@ApiResponse(responseCode = "404", description = "Не найдено") })
	public ResponseEntity<?> searchCardList(
			@Parameter(name = "keyword", required = true, description = "Ключевое слово.")
			@RequestParam(required = true, name = "keyword") String keyword,
			@Parameter(name = "skip", required = false, description = DESC_SKIP_SIZE) 
			@RequestParam(required = false, name = "skip") Integer skip,
			@Parameter(name = "take", required = false, description = DESC_TAKE_SIZE) 
			@RequestParam(required = false, name = "take") Integer take) {
		int skipVal = skip != null ? skip : VAL_SKIP_SIZE;
		int takeVal = take != null ? take : VAL_TAKE_SIZE;
		return new ResponseEntity<>(cardService.searchCardList(keyword, skipVal, takeVal), HttpStatus.OK);
	}
	
	@Operation (summary = "Получить сведения о количестве RFID Карт.")
	@RequestMapping(value="/api/Card/Count", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Long.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> count() {
		return new ResponseEntity<>(cardService.count(), HttpStatus.OK);
	}
	
	@Operation (summary = "Получить сведения о количестве RFID Карт по СКУД Id.")
	@RequestMapping(value="/api/Card/CountBytemplateSystemId", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Long.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> count(@Parameter(name = "templateSystemId", required = true, 
			description = "Идентификатор СКУД.")
			@RequestParam(required = true, name = "templateSystemId") UUID templateSystemId) {
		return new ResponseEntity<>(cardService.countBytemplateSystemId(templateSystemId), HttpStatus.OK);
	}
}
