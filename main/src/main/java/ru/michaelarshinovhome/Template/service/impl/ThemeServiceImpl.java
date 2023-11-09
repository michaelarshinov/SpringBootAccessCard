package ru.michaelarshinovhome.Template.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.michaelarshinovhome.Template.dao.ThemeRepository;
import ru.michaelarshinovhome.Template.dto.ThemeDto;
import ru.michaelarshinovhome.Template.dto.wrapped.ThemeDtoCollectionWrapped;
import ru.michaelarshinovhome.Template.model.Theme;
import ru.michaelarshinovhome.Template.service.ThemeService;

@Service(value = "themeService")
public class ThemeServiceImpl implements ThemeService {
	@Autowired
	ThemeRepository repository;
	
	@Override
	@Transactional
	public ThemeDtoCollectionWrapped findById(Integer id) {
		Theme theme  = null;
		ThemeDtoCollectionWrapped ret = null;
		Optional<Theme> optionalTheme = repository.findById(id);
		if (optionalTheme.isPresent()) {
			theme = optionalTheme.get();
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			try {
				ThemeDto data = objectMapper.readValue(theme.getThemeValue(), ThemeDto.class);
				data.setId(theme.getId());
				data.setThemeName(theme.getThemeName().getNameResourceKey());
				ret = new ThemeDtoCollectionWrapped();
				List<ThemeDto> keys = new ArrayList<>();
				ret.setCollectionLoaded(List.of(data));
				for (Theme t: repository.findAll()) {
					ThemeDto dto = new ThemeDto();
					dto.setId(t.getThemeName().getThemeId());
					dto.setThemeName(t.getThemeName().getNameResourceKey());
					keys.add(dto);
				}
				ret.setCollectionKeys(keys);
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

}
