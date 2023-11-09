package ru.michaelarshinovhome.Template.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import ru.michaelarshinovhome.Template.dto.AccessPointDto;
import ru.michaelarshinovhome.Template.dto.AccessPointDtoInput;
import ru.michaelarshinovhome.Template.dto.wrapped.AccessPointDtoOneWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.AccessPointDtoWrapped;

public interface AccessPointService {
	long count();
	long countByTemplateSystem(UUID templateSystemId);
	AccessPointDtoWrapped findAll();
	AccessPointDtoWrapped findAll(Pageable pageable);
	AccessPointDtoWrapped findAllbyACSId(UUID aCSId, Pageable pageable);
	AccessPointDtoWrapped findAllbyACSId(UUID aCSId);
	List<AccessPointDto> findAllUnwrapped();
	AccessPointDto create(AccessPointDtoInput accessPointObject);
	AccessPointDtoOneWrapped findById(UUID accessPointId);
}
