package ru.michaelarshinovhome.Template.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.michaelarshinovhome.Template.dao.DictionaryStaticRepository;
import ru.michaelarshinovhome.Template.dto.DictionaryStaticDto;
import ru.michaelarshinovhome.Template.dto.wrapped.DictionaryStaticDtoWrapped;
import ru.michaelarshinovhome.Template.model.DictionaryStatic;
import ru.michaelarshinovhome.Template.service.DictionaryStaticService;

@Service(value = "dictionaryStaticService")
public class DictionaryStaticServiceImpl implements DictionaryStaticService {

	@Autowired
	DictionaryStaticRepository dictionaryStaticRepository;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
	public DictionaryStaticDtoWrapped findAll() {
		List<DictionaryStaticDto> ret = new ArrayList<>();
		List<DictionaryStatic> languages = (List<DictionaryStatic>) dictionaryStaticRepository.findAll();
		if (languages != null) {
			languages.forEach(x->ret.add(new DictionaryStaticDto(x)));
		}
		return new DictionaryStaticDtoWrapped(ret);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
	@Cacheable(cacheNames = {"dictionaryStaticCache"}, key = "#parentId")
	public DictionaryStaticDtoWrapped findAllByParentId(int parentId) {
		List<DictionaryStaticDto> ret = new ArrayList<>();
		List<DictionaryStatic> languages = (List<DictionaryStatic>) dictionaryStaticRepository.findAllByParentId(parentId);
		if (languages != null) {
			languages.forEach(x->ret.add(new DictionaryStaticDto(x)));
		}
		return new DictionaryStaticDtoWrapped(ret, "Alert_GetDictionaryStaticNodeAsync_info");
	}

}
