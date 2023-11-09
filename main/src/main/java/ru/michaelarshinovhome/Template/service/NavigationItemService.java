package ru.michaelarshinovhome.Template.service;

import java.util.List;

import ru.michaelarshinovhome.Template.dto.NavigationItemModelDto;

public interface NavigationItemService {
	List<NavigationItemModelDto> findAllUnwrapped();
}
