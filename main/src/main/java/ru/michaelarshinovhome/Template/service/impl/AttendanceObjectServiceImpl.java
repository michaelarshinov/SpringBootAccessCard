package ru.michaelarshinovhome.Template.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.michaelarshinovhome.Template.dao.AttendanceObjectRepository;
import ru.michaelarshinovhome.Template.dto.AttendanceObjectDto;
import ru.michaelarshinovhome.Template.dto.AttendanceObjectDtoInput;
import ru.michaelarshinovhome.Template.dto.wrapped.AttendanceObjectDtoWrapped;
import ru.michaelarshinovhome.Template.model.AttendanceObject;
import ru.michaelarshinovhome.Template.service.AttendanceObjectService;

@Service("attendanceObjectService")
@Transactional
public class AttendanceObjectServiceImpl implements AttendanceObjectService {

	@Autowired
	AttendanceObjectRepository repository;

	@Override
	public AttendanceObjectDto addAttendanceObject(AttendanceObjectDtoInput objectToAdd) {		
		return new AttendanceObjectDto(repository.save(AttendanceObject.builder()
				.name(objectToAdd.getName())
				.path(objectToAdd.getPath())
				.acsId(objectToAdd.getTemplateSystemId())
				.build()));
	}

	@Override
	public AttendanceObjectDtoWrapped findAll(Pageable pageable) {
		List<AttendanceObjectDto> ret = new ArrayList<>();
		Page<AttendanceObject> acsList = (Page<AttendanceObject>) repository.findAll(pageable);
		if (acsList != null) {
			acsList.forEach(x->ret.add(new AttendanceObjectDto(x)));
		}
		return new AttendanceObjectDtoWrapped(ret);
	}

}
