package ru.michaelarshinovhome.Template.service;

import java.util.List;

import ru.michaelarshinovhome.Template.dto.PermissionNavigationItemModelDto;

public interface PermissionNavigationItemService {
	List<PermissionNavigationItemModelDto> findAllUnwrapped();
}
