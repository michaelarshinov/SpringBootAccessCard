package ru.michaelarshinovhome.Template.dto;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;
import ru.michaelarshinovhome.Template.model.AttendanceObject;

@Data
public class AttendanceObjectDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5143410992287812389L;
	private UUID id;
	private String name;
	private UUID templateSystemId;
	private String path;
	
	public AttendanceObjectDto(AttendanceObject attendanceObject) {
		this.id = attendanceObject.getId();
		this.name = attendanceObject.getName();
		this.templateSystemId = attendanceObject.getAcsId();
		this.path = attendanceObject.getPath();
	}
}
