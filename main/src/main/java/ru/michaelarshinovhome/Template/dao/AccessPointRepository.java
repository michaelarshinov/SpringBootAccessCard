package ru.michaelarshinovhome.Template.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.michaelarshinovhome.Template.model.AccessPoint;

@Repository
@Transactional
public interface AccessPointRepository extends 
	PagingAndSortingRepository<AccessPoint, UUID> {	
		Page<AccessPoint> findAllByAcsId(UUID id, Pageable pageable);
		long countByAcsId(UUID id);
		List<AccessPoint> findAllByAcsId(UUID id);
}
