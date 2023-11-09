package ru.michaelarshinovhome.Template.service;

import java.util.UUID;

public interface AccessZoneService {
	long count();
	long countByAcsId(UUID id);
	/*AccessZoneWithPointsDtoWrapped findAll();
	AccessZoneWithPointsDtoWrapped findAll(Pageable pageable);
	List<AccessZoneWithPointsDto> findAllUnwrapped();
	AccessZoneDto create(AccessZoneDtoInput templateSystemObject);
	AccessZoneWithPointsDtoWrapped findAllbyACSId(UUID aCSId, Pageable pageable);
	AccessZoneWithPointsDtoOneWrapped findById(UUID accessZoneId);*/
}
