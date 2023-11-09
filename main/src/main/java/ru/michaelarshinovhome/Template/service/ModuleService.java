package ru.michaelarshinovhome.Template.service;

import ru.michaelarshinovhome.Template.dto.wrapped.PluginInfoModelDtoWrapped;

public interface ModuleService {
	PluginInfoModelDtoWrapped getInfo(String url);
}
