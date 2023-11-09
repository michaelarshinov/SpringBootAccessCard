package ru.michaelarshinovhome.Template.dto;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;

@Data
public class AccessPointDtoInput implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3120363160971917043L;
	private String name;
	private UUID templateSystemId;
	private UUID attendanceObjectForExitId;
	private UUID attendanceObjectForEntranceId;
}
