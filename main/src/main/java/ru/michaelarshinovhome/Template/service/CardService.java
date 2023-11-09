package ru.michaelarshinovhome.Template.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import ru.michaelarshinovhome.Template.dto.RFIDCardDto;
import ru.michaelarshinovhome.Template.dto.RFIDCardDtoInput;
import ru.michaelarshinovhome.Template.dto.wrapped.RFIDCardDtoOneWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.RFIDCardDtoWrapped;

public interface CardService {
	long count();
	long countByAccessControlSystemId(UUID templateSystemId);
	RFIDCardDtoWrapped findAll(Pageable pageable);
	List<RFIDCardDto> findAllUnwrapped(Pageable pageable);
	RFIDCardDto create(RFIDCardDtoInput cardObject);
	RFIDCardDtoOneWrapped findById(UUID cardId);
	RFIDCardDtoWrapped findAllByTemplateSystemId(UUID templateSystemId, Pageable pageable);
	RFIDCardDtoWrapped findAllByTemplateSystemIdAndStatus(UUID templateSystemId, Short status, Pageable pageable);
	RFIDCardDtoWrapped searchCardList(String keyword, int skip, int take);
	RFIDCardDtoWrapped findAllByTemplateSystemIdsAndStatus(List<UUID> templateSystemIds, Short status, Pageable pageable);
}
