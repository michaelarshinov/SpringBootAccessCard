package ru.michaelarshinovhome.Template.dto;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;
import ru.michaelarshinovhome.Template.model.AccessPoint;

@Data
public class AccessPointDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3586176760031219922L;
	private UUID id;
	private String name;
	private UUID templateSystemId;
	private AttendanceObjectDto attendanceObjectForExit;
	private AttendanceObjectDto attendanceObjectForEntrance;
	
	public AccessPointDto(AccessPoint accessPoint) {
		this.id = accessPoint.getId();
		this.name = accessPoint.getName();
		this.attendanceObjectForExit = new AttendanceObjectDto(accessPoint.getAttendanceObjectForExit());
		this.attendanceObjectForEntrance = new AttendanceObjectDto(accessPoint.getAttendanceObjectForEntrance());
		this.templateSystemId = accessPoint.getAcsId();
	}
}
