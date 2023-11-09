package ru.michaelarshinovhome.Template.service;

import ru.michaelarshinovhome.Template.dto.wrapped.ThemeDtoCollectionWrapped;

public interface ThemeService {
	ThemeDtoCollectionWrapped findById(Integer id);
}
