package ru.michaelarshinovhome.Template.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import ru.michaelarshinovhome.Template.dto.AccessIdTypeDto;
import ru.michaelarshinovhome.Template.dto.wrapped.AccessEventDtoWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.AccessIdTypeDtoOneWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.AccessIdTypeDtoWrapped;

public interface AccessEventService {
	List<AccessIdTypeDto> getAccessIdTypesRaw(Integer language);
	AccessIdTypeDto getAccessIdTypeRaw(Integer language, Integer id);
	AccessIdTypeDtoWrapped getAccessIdTypes(Integer language);
	AccessIdTypeDtoOneWrapped getAccessIdType(Integer language, Integer id);
	Map<Integer, String> getAccessIdTypesMap(Integer language);
	long count();
	AccessEventDtoWrapped findAll(Pageable pageable, int languageId);
}
