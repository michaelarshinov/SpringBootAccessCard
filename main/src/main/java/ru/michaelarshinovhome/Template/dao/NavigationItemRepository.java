package ru.michaelarshinovhome.Template.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.michaelarshinovhome.Template.model.NavigationItem;

@Repository
public interface NavigationItemRepository extends CrudRepository<NavigationItem, Integer>{

}
