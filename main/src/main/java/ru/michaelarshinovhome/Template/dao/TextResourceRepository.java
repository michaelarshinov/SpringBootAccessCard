package ru.michaelarshinovhome.Template.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.michaelarshinovhome.Template.model.TextResource;
import ru.michaelarshinovhome.Template.model.TextResourcePK;

@Repository
@Transactional
public interface TextResourceRepository extends PagingAndSortingRepository<TextResource, TextResourcePK> {
	List<TextResource> findAllByResourceKey(String resourceKey);
	List<TextResource> findByResourceKeyAndLanguageId(String textResourceKey, Integer language);

}
