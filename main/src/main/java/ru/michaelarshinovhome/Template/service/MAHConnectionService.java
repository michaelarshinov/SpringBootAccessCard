package ru.michaelarshinovhome.Template.service;

import ru.michaelarshinovhome.Template.dto.DtoWrapper;
import ru.michaelarshinovhome.Template.dto.PluginInfoModel;
import ru.michaelarshinovhome.Template.dto.VCRConnectionAccountModelResultContainer;
import ru.michaelarshinovhome.Template.dto.VCRConnectionLogin;

public interface MAHConnectionService {
	MAHConnectionAccountModelResultContainer accountLogin(MAHConnectionLogin login);
	DtoWrapper saveModuleInfo(PluginInfoModel info);
	boolean isTokenValid(String jwtToken);
}
