package ru.michaelarshinovhome.Template.dao;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.michaelarshinovhome.Template.model.AccessEvent;

@Repository
@Transactional
public interface AccessEventRepository extends 
	PagingAndSortingRepository<AccessEvent, UUID> {	
		Page<AccessEvent> findAllByAcsId(UUID id, Pageable pageable);
		long countByAcsId(UUID id);
}
