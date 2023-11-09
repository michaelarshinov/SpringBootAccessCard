package ru.michaelarshinovhome.Template.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.Builder;
import lombok.Data;
import ru.michaelarshinovhome.Template.dto.DtoWrapper;
import ru.michaelarshinovhome.Template.dto.PluginInfoModel;
import ru.michaelarshinovhome.Template.dto.MAHConnectionAccountModelResultContainer;
import ru.michaelarshinovhome.Template.dto.MAHConnectionLogin;
import ru.michaelarshinovhome.Template.dto.wrapped.WrapperEmpty;
import ru.michaelarshinovhome.Template.service.MAHConnectionService;

@Service("MAHConnectionService")
@Configurable
public class MAHConnectionServiceImpl implements MAHConnectionService {
	private static final Logger logger = LoggerFactory.getLogger(MAHConnectionServiceImpl.class);
	
	@Autowired
	RestTemplate restTemplate;
	@Value(value = "${mah.server.url}")
	String MAHServerUrl;
	
	HasAccessPermission hasAccessPermission = null;
	@Value(value = "${auth.jwt.permission.url}")
	String permissionJwtAuthUrl;

	@Data
	@Builder
	static class HasAccessPermission {
		String methodName;
	}
	
	private HasAccessPermission getHasAccessPermissionObject() {
		if (hasAccessPermission == null) {
			hasAccessPermission = HasAccessPermission.builder().methodName(permissionJwtAuthUrl).build();
		}
		return hasAccessPermission;
	}
	
	@Override
	public MAHConnectionAccountModelResultContainer accountLogin(MAHConnectionLogin login) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<VCRConnectionLogin> request = 
				new HttpEntity<>(login, headers);
		MAHConnectionAccountModelResultContainer ret = 
				restTemplate.postForObject(MAHServerUrl + "/Account/Login", request, MAHConnectionAccountModelResultContainer.class);
		return ret;
	}

	@Override
	public DtoWrapper saveModuleInfo(PluginInfoModel info) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PluginInfoModel> request = 
				new HttpEntity<>(info, headers);
		DtoWrapper ret = 
				restTemplate.postForObject(MAHServerUrl + "/Module/SaveModuleInfo", 
						request, DtoWrapper.class);
		return ret;
	}
	
	@Override
	public boolean isTokenValid(String jwtToken) {
		if (StringUtils.isNotBlank(jwtToken)) {
//if (jwtToken.startsWith("token123")) {	return true; }
			HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    headers.add("Authorization", "Bearer " + jwtToken);
			HttpEntity<HasAccessPermission> request = 
					new HttpEntity<>(getHasAccessPermissionObject(), headers);
			try {
				WrapperEmpty ret = restTemplate.postForObject(MAHServerUrl + "/Permission/HasAccessPermission", 
						request, WrapperEmpty.class);
				return ret.isSuccess();
			} catch(Exception e) {
				logger.info(e.getMessage());
			}
		}
		return false;
	}

}
