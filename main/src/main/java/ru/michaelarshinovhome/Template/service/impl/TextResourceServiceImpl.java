package ru.michaelarshinovhome.Template.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.michaelarshinovhome.Template.dao.TextResourceRepository;
import ru.michaelarshinovhome.Template.dto.TextResourceDto;
import ru.michaelarshinovhome.Template.dto.wrapped.TextResourceDtoWrapped;
import ru.michaelarshinovhome.Template.model.TextResource;
import ru.michaelarshinovhome.Template.service.TextResourceService;

@Service(value = "textResourceService")
public class TextResourceServiceImpl implements TextResourceService {
	@Autowired
	TextResourceRepository textResourceRepository;
	
	@Override
	@Transactional
	public TextResourceDtoWrapped findAll() {
		return new TextResourceDtoWrapped(findAllUnwrapped());
	}

	@Override
	@Transactional
	public List<TextResourceDto> findAllUnwrapped() {
		List<TextResourceDto> ret = new ArrayList<>();
		List<TextResource> languages = (List<TextResource>) textResourceRepository.findAll();
		if (languages != null) {
			languages.forEach(x->ret.add(new TextResourceDto(x, 0)));
		}
		return ret;
	}

	@Override
	@Transactional
	public TextResourceDtoWrapped findAll(Pageable pageable) {
		List<TextResourceDto> ret = new ArrayList<>();
		Page<TextResource> languages = (Page<TextResource>) textResourceRepository.findAll(pageable);
		if (languages != null) {
			languages.forEach(x->ret.add(new TextResourceDto(x, 0)));
		}
		return new TextResourceDtoWrapped(ret);
	}

	@Override
	public List<TextResourceDto> findByResourceKey(String resourceKey) {
		List<TextResource> resources = textResourceRepository.findAllByResourceKey(resourceKey);
		List<TextResourceDto> ret = new ArrayList<>();
		if (resources != null) {
			ret = resources.stream().map(x-> new TextResourceDto(x,0)).toList();
		}
		return ret;
	}

	@Override
	public List<TextResourceDto> findByResourceKeyAndLanguage(String textResourceKey, Integer language) {
		List<TextResource> resources = textResourceRepository.findByResourceKeyAndLanguageId(textResourceKey, language);
		List<TextResourceDto> ret = new ArrayList<>();
		if (resources != null) {
			ret = resources.stream().map(x-> new TextResourceDto(x,0)).toList();
		}
		return ret;
	}
	
}
