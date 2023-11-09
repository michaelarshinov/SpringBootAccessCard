package ru.michaelarshinovhome.Template.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.michaelarshinovhome.Template.dto.PluginInfoModel;
import ru.michaelarshinovhome.Template.dto.wrapped.PluginInfoModelDtoWrapped;
import ru.michaelarshinovhome.Template.service.ModuleService;
import ru.michaelarshinovhome.Template.service.NavigationItemService;
import ru.michaelarshinovhome.Template.service.PermissionNavigationItemService;
import ru.michaelarshinovhome.Template.service.PermissionService;
import ru.michaelarshinovhome.Template.service.TextResourceService;

@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {
	@Value(value = "${module.guid}")
	String moduleGuid;
	
	@Value(value = "${module.name}")
	String moduleName;
	
	@Autowired
	TextResourceService textResourceService; 
	
	@Autowired
	NavigationItemService navigationService;
	
	@Autowired
	PermissionService permissionService;
	
	@Autowired
	PermissionNavigationItemService permissionNavigationItemService;
	
	@Override
	@Transactional
	public PluginInfoModelDtoWrapped getInfo(String url) {
		PluginInfoModel model = new PluginInfoModel();
		model.setTextResources(textResourceService.findAllUnwrapped());
		model.setNavigationItems(navigationService.findAllUnwrapped());
		model.setPermissions(permissionService.findAllUnwrapped());
		model.setPermissionNavigationItems(permissionNavigationItemService.findAllUnwrapped());
		model.setGuid(moduleGuid);
		model.setName(moduleName);
		model.setUrl(url);//2019-01-06T17:16:40
		//model.setRefreshDateTime(new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss").format(new Date()));
		//iso-8601
		TimeZone tz = TimeZone.getTimeZone("Europe/Moscow");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
		df.setTimeZone(tz);
		String nowAsISO = df.format(new Date());
		model.setRefreshDateTime(nowAsISO);
		model.setRefreshStatus(0);
		return new PluginInfoModelDtoWrapped(model);
	}

}
