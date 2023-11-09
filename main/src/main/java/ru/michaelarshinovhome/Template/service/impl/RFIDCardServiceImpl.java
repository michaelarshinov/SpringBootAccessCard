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
import ru.michaelarshinovhome.Template.dao.CardRepository;
import ru.michaelarshinovhome.Template.dto.TemplateSystemDto;
import ru.michaelarshinovhome.Template.dto.RFIDCardDto;
import ru.michaelarshinovhome.Template.dto.RFIDCardDtoInput;
import ru.michaelarshinovhome.Template.dto.UUIDAndDoubleDto;
import ru.michaelarshinovhome.Template.dto.wrapped.TemplateSystemDtoOneWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.RFIDCardDtoOneWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.RFIDCardDtoWrapped;
import ru.michaelarshinovhome.Template.model.TemplateSystem;
import ru.michaelarshinovhome.Template.model.RFIDCard;
import ru.michaelarshinovhome.Template.service.CardService;

@Service(value = "cardService")
@Transactional
public class RFIDCardServiceImpl implements CardService {
	private static final Logger logger = LoggerFactory.getLogger(RFIDCardServiceImpl.class);

	@Autowired
	CardRepository repository;

	@Autowired
	TemplateSystemRepository acsRepository;
	
	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public long countByTemplateSystemId(UUID templateSystemId) {
		return repository.countByTemplateSystemId(templateSystemId);
	} 
	
	@Override
	public RFIDCardDtoWrapped findAll(Pageable pageable) {
		return new RFIDCardDtoWrapped(findAllUnwrapped(pageable));
	}

	@Override
	public List<RFIDCardDto> findAllUnwrapped(Pageable pageable) {
		List<RFIDCardDto> ret = new ArrayList<>();
		Page<RFIDCard> acsList = (Page<RFIDCard>) repository.findAll(pageable);
		if (acsList != null) {
			acsList.stream().forEachOrdered(x -> ret.add(new RFIDCardDto(x)));
		}
		return ret;
	}

	@Override
	public RFIDCardDto create(RFIDCardDtoInput cardObject) {
		Optional<TemplateSystem> acs = acsRepository.findById(cardObject.getTemplateSystemId());
		if (acs.isEmpty()) return null;
		RFIDCard card = RFIDCard.builder()
				.status(cardObject.getStatus())
				.templateSystemName(acs.get().getName())
				.templateSystemId(cardObject.getTemplateSystemId())
				.readableCardNumber(cardObject.getReadableCardNumber())
				.serialCardNumber(cardObject.getSerialCardNumber())
				.cardNumberWithoutConversion(cardObject.getCardNumberWithoutConversion())
				.build();
		return new RFIDCardDto(repository.save(card));
	}

	@Override
	public RFIDCardDtoOneWrapped findById(UUID cardId) {
		Optional<RFIDCard> card = repository.findById(cardId);
		return new RFIDCardDtoOneWrapped(card.isPresent() ? 
				new RFIDCardDto(card.get()): null);
	}

	@Override
	public RFIDCardDtoWrapped findAllByTemplateSystemId(UUID templateSystemId, Pageable pageable) {
		List<RFIDCardDto> ret = new ArrayList<>();
		Page<RFIDCard> acsList = (Page<RFIDCard>) repository.findAllByTemplateSystemId(templateSystemId, pageable);
		if (acsList != null) {
			acsList.stream().forEachOrdered(x -> ret.add(new RFIDCardDto(x)));
		}
		return new RFIDCardDtoWrapped(ret);
	}

	@Override
	public RFIDCardDtoWrapped findAllByTemplateSystemIdAndStatus(UUID templateSystemId, Short status,
			Pageable pageable) {
		List<RFIDCardDto> ret = new ArrayList<>();
		Page<RFIDCard> acsList = (Page<RFIDCard>) repository.findAllByTemplateSystemIdAndStatus(templateSystemId, status, pageable);
		if (acsList != null) {
			acsList.stream().forEachOrdered(x -> ret.add(new RFIDCardDto(x)));
		}
		return new RFIDCardDtoWrapped(ret);
	}


	@Override
	public RFIDCardDtoWrapped findAllByTemplateSystemIdsAndStatus(List<UUID> templateSystemIds, Short status,
			Pageable pageable) {
		List<RFIDCardDto> ret = new ArrayList<>();
		Page<RFIDCard> acsList = (Page<RFIDCard>) repository.findAllByTemplateSystemIdInAndStatus(templateSystemIds, status, pageable);
		if (acsList != null) {
			acsList.stream().forEachOrdered(x -> ret.add(new RFIDCardDto(x)));
		}
		return new RFIDCardDtoWrapped(ret);
	}
	
	@Override
	public RFIDCardDtoWrapped searchCardList(String keyword, int skip, int take) {
		List<UUIDAndDoubleDto> rawList = repository.searchByCardsAndOwner(keyword, skip, take);
		logger.info(rawList+"");
		rawList.stream().forEachOrdered(x-> logger.info(x.getId()+" "+x.getDist()));
		List<RFIDCardDto> wrapped = new ArrayList<RFIDCardDto>();
		if (rawList != null) {
			rawList.stream().forEachOrdered(x -> wrapped.add(
					new RFIDCardDto(repository.findById(UUID.fromString(x.getId())))));
		}
		return new RFIDCardDtoWrapped(wrapped);
	}

}
