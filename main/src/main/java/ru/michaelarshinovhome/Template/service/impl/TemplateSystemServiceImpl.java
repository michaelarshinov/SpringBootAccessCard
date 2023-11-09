package ru.michaelarshinovhome.Template.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.michaelarshinovhome.Template.dao.TemplateSystemRepository;
import ru.michaelarshinovhome.Template.dto.TemplateSystemDto;
import ru.michaelarshinovhome.Template.dto.TemplateSystemDtoInput;
import ru.michaelarshinovhome.Template.dto.TemplateSystemDtoInputStatus;
import ru.michaelarshinovhome.Template.dto.TemplateSystemMinimalDto;
import ru.michaelarshinovhome.Template.dto.TemplateSystemNameUUIDPairsDto;
import ru.michaelarshinovhome.Template.dto.wrapped.TemplateSystemDtoOneWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.TemplateSystemDtoWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.TemplateSystemMinimalDtoWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.TemplateSystemNameUUIDPairsDtoWrapped;
import ru.michaelarshinovhome.Template.model.TemplateSystem;
import ru.michaelarshinovhome.Template.service.TemplateSystemService;

@Service(value = "templateSystemService")
public class TemplateSystemServiceImpl implements TemplateSystemService {
	private static final Logger logger = LoggerFactory.getLogger(TemplateSystemServiceImpl.class);

	@Autowired
	TemplateSystemRepository repository;
	
	@Override
	@Transactional
	public TemplateSystemDtoWrapped findAll() {
		return new TemplateSystemDtoWrapped(findAllUnwrapped());
	}

	@Override
	@Transactional
	public TemplateSystemDtoWrapped findAll(Pageable pageable) {
		List<TemplateSystemDto> ret = new ArrayList<>();
		Page<TemplateSystem> acsList = (Page<TemplateSystem>) repository.findAll(pageable);
		if (acsList != null) {
			acsList.forEach(x->ret.add(new TemplateSystemDto(x)));
		}
		return new TemplateSystemDtoWrapped(ret);
	}

	@Override
	@Transactional
	public List<TemplateSystemDto> findAllUnwrapped() {
		List<TemplateSystemDto> ret = new ArrayList<>();
		List<TemplateSystem> acsList = (List<TemplateSystem>) repository.findAll();
		if (acsList != null) {
			acsList.forEach(x -> ret.add(new TemplateSystemDto(x)));
		}
		return ret;
	}

	@Override
	@Transactional
	public long count() {
		return repository.count();
	}

	@Override
	@Transactional
	public TemplateSystemDto create(TemplateSystemDtoInput templateSystemObject) {
		TemplateSystem templateSystem = TemplateSystem
				.builder().name(templateSystemObject.getName())
				.version(templateSystemObject.getVersion())
				.enabled(templateSystemObject.isEnabled())
				.brand(templateSystemObject.getBrand())
				.logo(templateSystemObject.getLogo())
				.build();
		return new TemplateSystemDto(repository.save(templateSystem));
	}

	@Override
	@Transactional
	public TemplateSystemDtoOneWrapped findById(UUID templateSystemId) {
		Optional<TemplateSystem> templateSystem = repository.findById(templateSystemId);
		return new TemplateSystemDtoOneWrapped(templateSystem.isPresent() ? 
				new TemplateSystemDto(templateSystem.get()): null);
	}
	/*
	@Override
	@Transactional
	public TemplateSystemPointsViewDtoWrapped findAllPointsView() {
		return new TemplateSystemPointsViewDtoWrapped(findAllUnwrappedPointsView());
	}

	@Override
	@Transactional
	public List<TemplateSystemPointsViewDto> findAllUnwrappedPointsView() {
		List<TemplateSystemPointsViewDto> ret = new ArrayList<>();
		List<TemplateSystem> acsList = (List<TemplateSystem>) repository.findAll();
		if (acsList != null) {
			acsList.forEach(x -> ret.add(new TemplateSystemPointsViewDto(x)));
		}
		return ret;
	}

	@Override
	@Transactional
	public TemplateSystemZonesViewDtoWrapped findAllZonesView() {
		return new TemplateSystemZonesViewDtoWrapped(findAllUnwrappedZonesView());
	}

	@Override
	@Transactional
	public List<TemplateSystemZonesViewDto> findAllUnwrappedZonesView() {
		List<TemplateSystemZonesViewDto> ret = new ArrayList<>();
		List<TemplateSystem> acsList = (List<TemplateSystem>) repository.findAll();
		if (acsList != null) {
			acsList.forEach(x -> ret.add(new TemplateSystemZonesViewDto(x)));
		}
		return ret;
	}
*/
	@Override
	@Transactional
	public TemplateSystemDto changeStatus(TemplateSystemDtoInputStatus templateSystemObject) {
		Optional<TemplateSystem> acsOptional = repository.findById(templateSystemObject.getId());
		if (acsOptional.isPresent()) {
			TemplateSystem acs = acsOptional.get();
			acs.setEnabled(templateSystemObject.isEnabled());
			return new TemplateSystemDto(repository.save(acs));
		}
		return null;
	}
	/*
	@Override
	@Transactional
	public TemplateSystemPointsViewDtoOneWrapped findByIdPointsView(UUID templateSystemId) {
		Optional<TemplateSystem> templateSystem = repository.findById(templateSystemId);
		return new TemplateSystemPointsViewDtoOneWrapped(templateSystem.isPresent() ? 
				new TemplateSystemPointsViewDto(templateSystem.get()): null);
	}

	@Override
	public TemplateSystemZonesViewDtoOneWrapped findByIdZonesView(UUID templateSystemId) {
		Optional<TemplateSystem> templateSystem = repository.findById(templateSystemId);
		return new TemplateSystemZonesViewDtoOneWrapped(templateSystem.isPresent() ? 
				new TemplateSystemZonesViewDto(templateSystem.get()): null);
	}
*/
	@Override
	public TemplateSystemNameUUIDPairsDtoWrapped findAllNameUUIDPairs() {
		List<TemplateSystemNameUUIDPairsDto> ret = new ArrayList<>();
		List<TemplateSystem> acsList = (List<TemplateSystem>) repository.findAll();
		if (acsList != null) {
			acsList.stream().forEachOrdered(x -> ret.add(new TemplateSystemNameUUIDPairsDto(x)));
		}
		return new TemplateSystemNameUUIDPairsDtoWrapped(ret);
	}

	@Override
	public TemplateSystemMinimalDtoWrapped findAllMinimal() {
		List<TemplateSystemMinimalDto> ret = new ArrayList<>();
		List<TemplateSystem> acsList = (List<TemplateSystem>) repository.findAll();
		if (acsList != null) {
			acsList.stream().forEachOrdered(x -> ret.add(new TemplateSystemMinimalDto(x)));
		}
		return new TemplateSystemMinimalDtoWrapped(ret);
	}

}
