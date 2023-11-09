package ru.michaelarshinovhome.Template.dto;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;

@Data
public class AttendanceObjectDtoInput implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8575193166379004808L;
	private String name;
	private UUID templateSystemId;
	private String path;
}
