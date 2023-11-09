package ru.michaelarshinovhome.Template.service;

import java.util.List;

import ru.michaelarshinovhome.Template.dto.PermissionModelDto;

public interface PermissionService {
	List<PermissionModelDto> findAllUnwrapped();
}
