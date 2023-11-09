package ru.michaelarshinovhome.Template.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import ru.michaelarshinovhome.Template.dto.AccessIdTypeDto;
import ru.michaelarshinovhome.Template.dto.TextResourceDto;
import ru.michaelarshinovhome.Template.dto.wrapped.TextResourceDtoWrapped;

public interface TextResourceService {
	TextResourceDtoWrapped findAll();
	TextResourceDtoWrapped findAll(Pageable pageable);
	List<TextResourceDto> findAllUnwrapped();
	List<TextResourceDto> findByResourceKey(String resourceKey);
	List<TextResourceDto> findByResourceKeyAndLanguage(String textResourceKey, Integer language);
}
