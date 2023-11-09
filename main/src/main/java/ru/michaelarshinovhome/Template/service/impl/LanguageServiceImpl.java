package ru.michaelarshinovhome.Template.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.michaelarshinovhome.Template.dao.LanguageRepository;
import ru.michaelarshinovhome.Template.dto.LanguageDto;
import ru.michaelarshinovhome.Template.dto.wrapped.LanguageDtoWrapped;
import ru.michaelarshinovhome.Template.model.Language;
import ru.michaelarshinovhome.Template.service.LanguageService;

@Service(value = "languageService")
public class LanguageServiceImpl implements LanguageService {
	@Autowired
	LanguageRepository languageRepository;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
	public LanguageDtoWrapped findAll() {
		List<LanguageDto> ret = new ArrayList<>();
		List<Language> languages = (List<Language>) languageRepository.findAll();
		if (languages != null) {
			languages.forEach(x->ret.add(new LanguageDto(x)));
		}
		return new LanguageDtoWrapped(ret);
	}

}
