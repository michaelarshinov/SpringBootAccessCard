package ru.michaelarshinovhome.Template.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ru.michaelarshinovhome.Template.model.Log;

public interface LogRepository extends CrudRepository<Log, Integer> {
	
	@Modifying
	@Query(nativeQuery = true, value ="delete from \"Log\" where \"Date\" < "
			+ "now() - (interval '1' day) * :days")
	void deleteLogs(@Param(value = "days") int days);
	
}
