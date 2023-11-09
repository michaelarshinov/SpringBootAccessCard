package ru.michaelarshinovhome.Template.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.michaelarshinovhome.Template.model.Theme;

@Repository
@Transactional
public interface ThemeRepository extends CrudRepository<Theme, Integer>{

}
