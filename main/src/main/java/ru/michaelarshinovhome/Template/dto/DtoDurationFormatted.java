package ru.michaelarshinovhome.Template.dto;

public interface DtoDurationFormatted {
	default String durationFormatted(Integer duration) {
		String formatted = "";
		if (duration != null) {
			int temp = duration;
			int minutes = temp % 60;
			if (minutes<10) formatted+="0";
			formatted = formatted + minutes+" мин";
			temp/=60;
			if (temp>0) {
				int hours = temp % 24;
				if (hours>0) {
					formatted = hours + " ч " + formatted;
				}
				temp/=24;
				if (temp>0) {
					formatted = temp + " д " + formatted;
				}
			}
		}
		
		return formatted;
	}
}
