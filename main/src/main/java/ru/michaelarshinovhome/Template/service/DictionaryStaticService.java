package ru.michaelarshinovhome.Template.service;

import ru.michaelarshinovhome.Template.dto.wrapped.DictionaryStaticDtoWrapped;

public interface DictionaryStaticService {
	DictionaryStaticDtoWrapped findAll();
	DictionaryStaticDtoWrapped findAllByParentId(int parentId);
}
