package ru.michaelarshinovhome.Template.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.michaelarshinovhome.Template.dao.PermissionRepository;
import ru.michaelarshinovhome.Template.dto.PermissionModelDto;
import ru.michaelarshinovhome.Template.model.Permission;
import ru.michaelarshinovhome.Template.service.PermissionService;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	PermissionRepository repository;
	
	@Override
	public List<PermissionModelDto> findAllUnwrapped() {
		List<Permission> list = (List<Permission>) repository.findAll();
		List<PermissionModelDto> ret = new ArrayList<>();
		if (list != null) {
			list.stream().forEach(x->ret.add(new PermissionModelDto(x)));
		}
		return ret;
	}

}
