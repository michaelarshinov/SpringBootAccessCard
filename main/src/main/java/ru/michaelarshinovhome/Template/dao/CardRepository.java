package ru.michaelarshinovhome.Template.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.michaelarshinovhome.Template.dto.UUIDAndDoubleDto;
import ru.michaelarshinovhome.Template.model.RFIDCard;

@Repository
@Transactional
public interface CardRepository extends 
	PagingAndSortingRepository<RFIDCard, UUID> {
	Page<RFIDCard> findAllByTemplateSystemId(UUID templateSystemId, Pageable pageable);
	Page<RFIDCard> findAllByTemplateSystemIdAndStatus(UUID templateSystemId, Short status, Pageable pageable);
	Page<RFIDCard> findAllByTemplateSystemIdInAndStatus(List<UUID> templateSystemIds, Short status,
			Pageable pageable);
	@Modifying
	@Query(nativeQuery = true, value = 
		 "select cast(\"Id\" as text) as id, fullvisitingpage(\"ReadableCardNumber\",\"SerialCardNumber\",\"CardNumberWithoutConversion\",\"PersonName\")"+
		 "<-> :searchString as dist from \"Card\""+
		 //" union "+
		 //"select cast( \"Id\" as text) as id, fullvisitingpage(\"ReadableCardNumber\",\"SerialCardNumber\",\"CardNumberWithoutConversion\",\"PersonName\")"+
		 //"<-> replace_unaccent_eng(:searchString) as dist from \"Card\""+
		 //" union "+
		 //"select cast( \"Id\" as text) as id, fullvisitingpage(\"ReadableCardNumber\",\"SerialCardNumber\",\"CardNumberWithoutConversion\",\"PersonName\")"+
		 //"<-> replace_unaccent_eng_translit(:searchString) as dist from \"Card\""+
		 "order by dist asc limit :limit offset :offset"
	)
	List<UUIDAndDoubleDto> searchByCardsAndOwner(@Param("searchString") String searchString, 
			@Param("offset") int offset, @Param("limit") int limit);
	
	long countByTemplateSystemId(UUID templateSystemId);

}
