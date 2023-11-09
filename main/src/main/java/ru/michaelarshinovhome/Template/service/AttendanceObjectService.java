package ru.michaelarshinovhome.Template.service;

import org.springframework.data.domain.Pageable;

import ru.michaelarshinovhome.Template.dto.AttendanceObjectDto;
import ru.michaelarshinovhome.Template.dto.AttendanceObjectDtoInput;
import ru.michaelarshinovhome.Template.dto.wrapped.AttendanceObjectDtoWrapped;

public interface AttendanceObjectService {
	AttendanceObjectDto addAttendanceObject(AttendanceObjectDtoInput objectToAdd);
	AttendanceObjectDtoWrapped findAll(Pageable pageable);
}
