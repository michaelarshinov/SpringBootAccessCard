package ru.michaelarshinovhome.Template.service;

import ru.michaelarshinovhome.Template.model.Log;

public interface LogService {
	Log save(Log log);
	long count();
	void deleteLogs(int days);
}
