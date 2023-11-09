package ru.michaelarshinovhome.Template.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.michaelarshinovhome.Template.model.DictionaryStatic;

@Repository
@Transactional
public interface DictionaryStaticRepository extends CrudRepository<DictionaryStatic, Integer>{
	List<DictionaryStatic> findAllByParentId(Integer parentId);
}
