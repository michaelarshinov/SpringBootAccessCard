package ru.michaelarshinovhome.Template.dao;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.michaelarshinovhome.Template.model.TemplateSystem;

@Repository
@Transactional
public interface TemplateSystemRepository extends 
	PagingAndSortingRepository<TemplateSystem, UUID> {	

}
