package ru.michaelarshinovhome.Template.dao;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import ru.insyres.AccessControl.model.AttendanceObject;

public interface AttendanceObjectRepository extends PagingAndSortingRepository<AttendanceObject, UUID>{

}
