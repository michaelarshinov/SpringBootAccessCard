package ru.michaelarshinovhome.Template.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class TemplateSystemDtoInput implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4240240554451386148L;
	private String name;
	private String version;
	private boolean enabled;
	private String logo;
	private String brand;
}
