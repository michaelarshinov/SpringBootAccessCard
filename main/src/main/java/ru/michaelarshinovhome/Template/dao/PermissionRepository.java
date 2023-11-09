package ru.michaelarshinovhome.Template.dao;

import org.springframework.data.repository.CrudRepository;

import ru.michaelarshinovhome.Template.model.Permission;

public interface PermissionRepository extends CrudRepository<Permission, Integer> {

}
