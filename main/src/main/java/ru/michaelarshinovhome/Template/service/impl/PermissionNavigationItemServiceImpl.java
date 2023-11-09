package ru.michaelarshinovhome.Template.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.michaelarshinovhome.Template.dao.PermissionRepository;
import ru.michaelarshinovhome.Template.dto.PermissionNavigationItemModelDto;
import ru.michaelarshinovhome.Template.model.NavigationItem;
import ru.michaelarshinovhome.Template.model.Permission;
import ru.michaelarshinovhome.Template.service.PermissionNavigationItemService;

@Service("permissionNavigationItemService")
public class PermissionNavigationItemServiceImpl implements PermissionNavigationItemService {
	
	@Autowired
	public PermissionRepository permissionRepository;
	
	@Override
	@Transactional
	public List<PermissionNavigationItemModelDto> findAllUnwrapped() {
		List<Permission> permissions = (List<Permission>) permissionRepository.findAll();
		List<PermissionNavigationItemModelDto> ret = new ArrayList<>();
		if (permissions != null) {
			permissions.stream().forEach(x->{
				List<NavigationItem> navigationItems = ((Permission)x).getNavigationItems();
				if (navigationItems!=null) {
					for (NavigationItem ni:navigationItems) {
						ret.add(new PermissionNavigationItemModelDto(x.getId(),ni.getId()));
					}
				}
			});
		}
		return ret;
	}

}
