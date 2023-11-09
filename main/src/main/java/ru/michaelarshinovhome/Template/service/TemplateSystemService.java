package ru.michaelarshinovhome.Template.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import ru.michaelarshinovhome.Template.dto.TemplateSystemDto;
import ru.michaelarshinovhome.Template.dto.TemplateSystemDtoInput;
import ru.michaelarshinovhome.Template.dto.TemplateSystemDtoInputStatus;
import ru.michaelarshinovhome.Template.dto.wrapped.TemplateSystemDtoOneWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.TemplateSystemDtoWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.TemplateSystemMinimalDtoWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.TemplateSystemNameUUIDPairsDtoWrapped;

public interface TemplateSystemService {
	long count();
	TemplateSystemDtoWrapped findAll();
	TemplateSystemDtoWrapped findAll(Pageable pageable);
	TemplateSystemNameUUIDPairsDtoWrapped findAllNameUUIDPairs();
	List<TemplateSystemDto> findAllUnwrapped();
	TemplateSystemDto create(TemplateSystemDtoInput templateSystemObject);
	TemplateSystemDtoOneWrapped findById(UUID templateSystemId);
	/*TemplateSystemPointsViewDtoOneWrapped findByIdPointsView(UUID templateSystemId);
	TemplateSystemZonesViewDtoOneWrapped findByIdZonesView(UUID templateSystemId);
	TemplateSystemPointsViewDtoWrapped findAllPointsView();
	List<TemplateSystemPointsViewDto> findAllUnwrappedPointsView();
	TemplateSystemZonesViewDtoWrapped findAllZonesView();
	List<TemplateSystemZonesViewDto> findAllUnwrappedZonesView();*/
	TemplateSystemDto changeStatus(TemplateSystemDtoInputStatus templateSystemObject);
	TemplateSystemMinimalDtoWrapped findAllMinimal();
}
