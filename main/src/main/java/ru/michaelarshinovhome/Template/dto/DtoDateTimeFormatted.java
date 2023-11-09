package ru.michaelarshinovhome.Template.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public interface DtoDateTimeFormatted {
	default String formatTime(Timestamp timestamp) {
		if (timestamp == null) return "";
		return new SimpleDateFormat("HH:mm").format(timestamp);
	}
	
	default String formatDate(Timestamp timestamp) {
		if (timestamp == null) return "";
		return new SimpleDateFormat("dd.MM.yyyy").format(timestamp);
	}
}
