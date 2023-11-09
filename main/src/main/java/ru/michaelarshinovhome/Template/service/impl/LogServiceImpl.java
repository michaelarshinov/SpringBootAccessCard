package ru.michaelarshinovhome.Template.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.michaelarshinovhome.Template.dao.LogRepository;
import ru.michaelarshinovhome.Template.model.Log;
import ru.michaelarshinovhome.Template.service.LogService;

@Service("logService")
public class LogServiceImpl implements LogService {

	@Autowired
	LogRepository repository;
	
	@Override
	@Transactional
	public Log save(Log log) {
		log.setException(StringUtils.abbreviate(log.getException(), Log.EXCEPTION_LENGTH));
		log.setLevel(StringUtils.abbreviate(log.getLevel(), Log.LEVEL_LENGTH));
		log.setLogger(StringUtils.abbreviate(log.getLogger(), Log.LOGGER_LENGTH));
		log.setMessage(StringUtils.abbreviate(log.getMessage(), Log.MESSAGE_LENGTH));
		repository.save(log);
		return repository.save(log);
	}

	@Override
	@Transactional
	public long count() {
		return repository.count();
	}

	@Override
	@Transactional
	public void deleteLogs(int days) {
		repository.deleteLogs(days);
	}

}
