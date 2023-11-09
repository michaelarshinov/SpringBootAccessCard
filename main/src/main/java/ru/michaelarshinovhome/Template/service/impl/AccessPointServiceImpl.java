package ru.michaelarshinovhome.Template.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.michaelarshinovhome.Template.dao.AccessPointRepository;
import ru.michaelarshinovhome.Template.dao.AttendanceObjectRepository;
import ru.michaelarshinovhome.Template.dto.TemplateSystemDto;
import ru.michaelarshinovhome.Template.dto.AccessPointDto;
import ru.michaelarshinovhome.Template.dto.AccessPointDtoInput;
import ru.michaelarshinovhome.Template.dto.wrapped.AccessPointDtoOneWrapped;
import ru.michaelarshinovhome.Template.dto.wrapped.AccessPointDtoWrapped;
import ru.michaelarshinovhome.Template.model.TemplateSystem;
import ru.michaelarshinovhome.Template.model.AccessPoint;
import ru.michaelarshinovhome.Template.model.AccessZone;
import ru.michaelarshinovhome.Template.model.AttendanceObject;
import ru.michaelarshinovhome.Template.service.AccessPointService;

@Service("accessPointService")
public class AccessPointServiceImpl implements AccessPointService {

	@Autowired
	AccessPointRepository accessPointRepository;
	
	@Autowired
	AttendanceObjectRepository attendanceObjectRepository;
	
	@Override
	@Transactional
	public long count() {
		return accessPointRepository.count();
	}

	@Override
	@Transactional
	public long countByTemplateSystem(UUID templateSystemId) {
		return accessPointRepository.countByAcsId(templateSystemId);
	}

	@Override
	@Transactional
	public AccessPointDtoWrapped findAll() {
		return new AccessPointDtoWrapped(findAllUnwrapped());
	}

	@Override
	@Transactional
	public AccessPointDtoWrapped findAll(Pageable pageable) {
		List<AccessPointDto> ret = new ArrayList<>();
		Page<AccessPoint> acsList = (Page<AccessPoint>) accessPointRepository.findAll(pageable);
		if (acsList != null) {
			acsList.forEach(x -> ret.add(new AccessPointDto(x)));
		}
		return new AccessPointDtoWrapped(ret);
	}

	@Override
	@Transactional
	public List<AccessPointDto> findAllUnwrapped() {
		List<AccessPointDto> ret = new ArrayList<>();
		List<AccessPoint> acsList = (List<AccessPoint>) accessPointRepository.findAll();
		if (acsList != null) {
			acsList.forEach(x -> ret.add(new AccessPointDto(x)));
		}
		return ret;
	}

	@Override
	@Transactional
	public AccessPointDto create(AccessPointDtoInput accessPointObject) {
		Optional<AttendanceObject> exitO = attendanceObjectRepository
				.findById(accessPointObject.getAttendanceObjectForExitId());
		Optional<AttendanceObject> entranceO = attendanceObjectRepository
				.findById(accessPointObject.getAttendanceObjectForEntranceId());
		if (exitO.isEmpty() || entranceO.isEmpty()) return null;
		AttendanceObject exit = exitO.get();
		AttendanceObject entrance = entranceO.get();
		if (exit.getAcsId().compareTo(entrance.getAcsId()) != 0) return null;
		if (accessPointObject.getTemplateSystemId() == null || 
				accessPointObject.getTemplateSystemId().compareTo(entrance.getAcsId()) != 0) return null;
		AccessPoint accessPoint = AccessPoint
				.builder().name(accessPointObject.getName())
				.acsId(accessPointObject.getTemplateSystemId())
				.attendanceObjectForExitId(exit.getId())
				.attendanceObjectForEntranceId(entrance.getId())
				.attendanceObjectForExit(exit)
				.attendanceObjectForEntrance(entrance)
				.build();
		AccessPoint created = accessPointRepository.save(accessPoint);
		return new AccessPointDto(created);
	}

	@Override
	@Transactional
	public AccessPointDtoOneWrapped findById(UUID accessPointId) {
		Optional<AccessPoint> accessPoint = accessPointRepository.findById(accessPointId);
		return new AccessPointDtoOneWrapped(accessPoint.isPresent() ? 
				new AccessPointDto(accessPoint.get()): null);
	}

	@Override
	@Transactional
	public AccessPointDtoWrapped findAllbyACSId(UUID aCSId, Pageable pageable) {
		List<AccessPointDto> ret = new ArrayList<>();
		Page<AccessPoint> acsList = (Page<AccessPoint>) accessPointRepository.findAllByAcsId(aCSId, pageable);
		if (acsList != null) {
			acsList.forEach(x -> ret.add(new AccessPointDto(x)));
		}
		return new AccessPointDtoWrapped(ret);
	}

	@Override
	@Transactional
	public AccessPointDtoWrapped findAllbyACSId(UUID aCSId) {
		List<AccessPointDto> ret = new ArrayList<>();
		List<AccessPoint> acsList = (List<AccessPoint>) accessPointRepository.findAllByAcsId(aCSId);
		if (acsList != null) {
			acsList.forEach(x -> ret.add(new AccessPointDto(x)));
		}
		return new AccessPointDtoWrapped(ret);
	}

}
