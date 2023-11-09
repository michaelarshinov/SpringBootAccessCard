package ru.michaelarshinovhome.Template.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.michaelarshinovhome.Template.model.Language;

@Repository
@Transactional
public interface LanguageRepository extends CrudRepository<Language, Integer>{

}
