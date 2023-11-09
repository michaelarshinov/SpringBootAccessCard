package ru.michaelarshinovhome.Template.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.michaelarshinovhome.Template.dao.OffsetBasedPageRequest;
import ru.michaelarshinovhome.Template.dto.AttendanceObjectDto;
import ru.michaelarshinovhome.Template.dto.AttendanceObjectDtoInput;
import ru.michaelarshinovhome.Template.dto.wrapped.AccessControlSystemDtoWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.AttendanceObjectDtoOneWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.AttendanceObjectDtoWrapped;
import ru.michaelarshinovhome.Template.service.AttendanceObjectService;

@RestController
@Tag(name = "Attendance Object", description = "Контроллер для работы с множествами Объектов Посещения.")
public class AttendanceObjectRestController implements RestControllerSortable {
	
	private static final Logger logger = LoggerFactory.getLogger(AttendanceObjectRestController.class);
	
	@Autowired
	AttendanceObjectService attendanceObjectService;
	
	@Operation(summary = "Метод добавления Объекта посещения в определенной СКУД.")
	@RequestMapping(value="/api/AttendanceObject/Create", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = AttendanceObjectDtoOneWrapped.class)))),
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
	
	@Operation(summary = "Метод получения сведений о всех доступных Объектах Посещений.")
	@RequestMapping(value="/api/AttendanceObject/GetAll/{pageSize}/{pageNumber}", method=RequestMethod.GET)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Успешно найдено", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = AttendanceObjectDtoWrapped.class)))),
	        @ApiResponse(responseCode = "404", description = "Не найдено")})
	public ResponseEntity<?> findAll(@Parameter(name = "pageSize", required = false, 
			description = DESC_PAGE_SIZE)
		@PathVariable(required = false, name = "pageSize") Integer pageSize,
		@Parameter(name = "pageNumber", required = false, 
			description = DESC_PAGE_NUMBER)
		@PathVariable(required = false, name="pageNumber") Integer pageNumber) {
		int pageSizeVal = pageSize!=null ? pageSize : VAL_PAGE_SIZE;
		int pageNumberVal = pageNumber!=null ? pageNumber-1 : VAL_PAGE_NUMBER-1;
		Pageable pageable = PageRequest.of(pageNumberVal, pageSizeVal);
		return new ResponseEntity<>(attendanceObjectService.findAll(pageable), HttpStatus.OK);
	}
	
	@Operation(summary = "Метод получения сведений о всех доступных Объектах Посещений.")
	@RequestMapping(value = "/api/AttendanceObject/GetAll", method = RequestMethod.GET)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Успешно найдено", 
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = AttendanceObjectDtoWrapped.class)))),
			@ApiResponse(responseCode = "404", description = "Не найдено") })
	public ResponseEntity<?> findAllSkipTake(
			@Parameter(name = "skip", required = false, description = DESC_SKIP_SIZE) 
			@RequestParam(required = false, name = "skip") Integer skip,
			@Parameter(name = "take", required = false, description = DESC_TAKE_SIZE) 
			@RequestParam(required = false, name = "take") Integer take) {
		int skipVal = skip != null ? skip : VAL_SKIP_SIZE;
		int takeVal = take != null ? take : VAL_TAKE_SIZE;
		Pageable pageable = new OffsetBasedPageRequest(skipVal, takeVal);
		return new ResponseEntity<>(attendanceObjectService.findAll(pageable), HttpStatus.OK);
	}
}
