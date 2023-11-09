package ru.michaelarshinovhome.Template.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.michaelarshinovhome.Template.dao.AccessEventRepository;
import ru.michaelarshinovhome.Template.dto.TemplateSystemDto;
import ru.michaelarshinovhome.Template.dto.AccessEventDto;
import ru.michaelarshinovhome.Template.dto.AccessIdTypeDto;
import ru.michaelarshinovhome.Template.dto.DictionaryStaticDto;
import ru.michaelarshinovhome.Template.dto.TextResourceDto;
import ru.michaelarshinovhome.Template.dto.wrapped.AccessEventDtoWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.AccessIdTypeDtoOneWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.AccessIdTypeDtoWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.DictionaryStaticDtoWrapped;
import ru.michaelarshinovhome.Template.model.TemplateSystem;
import ru.michaelarshinovhome.Template.model.AccessEvent;
import ru.michaelarshinovhome.Template.service.AccessEventService;
import ru.michaelarshinovhome.Template.service.DictionaryStaticService;
import ru.michaelarshinovhome.Template.service.TextResourceService;

@Service(value = "accessEventService")
@Transactional
public class AccessEventServiceImpl implements AccessEventService {

	@Autowired
	DictionaryStaticService dictionaryStaticService;

	@Autowired
	TextResourceService textResourceService;

	@Autowired
	AccessEventRepository accessEventRepository;
	
	@Override
	@Cacheable(cacheNames = { "accessIdTypesRaw" }, key = "#language")
	public List<AccessIdTypeDto> getAccessIdTypesRaw(Integer language) {
		// List<TextResourceDto> accessIdType =
		// textResourceService.findByResourceKey("TemplateSystem_DictionaryStatic_AccessIdType");
		DictionaryStaticDtoWrapped accessIdTypesDto = dictionaryStaticService.findAllByParentId(3);
		System.out.println(accessIdTypesDto.getContent());
		List<DictionaryStaticDto> keys = accessIdTypesDto.getContent().stream()
				/* .filter(x -> x.getLanguageId() == language) */.toList();
		List<AccessIdTypeDto> ret = new ArrayList<>();
		keys.forEach(x -> {
			int id = x.getId();
			TextResourceDto resource = textResourceService.findByResourceKeyAndLanguage(x.getTextResourceKey(), language).get(0);
			ret.add(new AccessIdTypeDto(id, resource.getTextValue()));
		});
		return ret;
	}

	@Override
	public AccessIdTypeDto getAccessIdTypeRaw(Integer language, Integer id) {
		return getAccessIdTypesRaw(language).stream().filter(x -> x.getId() == id).findAny().get();
	}

	@Override
	public AccessIdTypeDtoWrapped getAccessIdTypes(Integer language) {
		return new AccessIdTypeDtoWrapped(getAccessIdTypesRaw(language));
	}

	@Override
	public AccessIdTypeDtoOneWrapped getAccessIdType(Integer language, Integer id) {
		return new AccessIdTypeDtoOneWrapped(getAccessIdTypeRaw(language, id));
	}

	@Override
	@Cacheable(cacheNames = { "accessIdTypesMap" }, key = "#language")
	public Map<Integer, String> getAccessIdTypesMap(Integer language) {
		return getAccessIdTypesRaw(language).stream()
				.collect(Collectors.toMap(AccessIdTypeDto::getId, AccessIdTypeDto::getName));
	}

	@Override
	public long count() {
		return accessEventRepository.count();
	}

	@Override
	public AccessEventDtoWrapped findAll(Pageable pageable, int languageId) {
		List<AccessEventDto> ret = new ArrayList<>();
		Page<AccessEvent> acsList = (Page<AccessEvent>) accessEventRepository.findAll(pageable);
		if (acsList != null) {
			acsList.forEach(x -> ret.add(new AccessEventDto(x)));
		}
		return new AccessEventDtoWrapped(ret);
	}

}
