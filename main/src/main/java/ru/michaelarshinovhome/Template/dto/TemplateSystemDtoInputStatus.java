package ru.michaelarshinovhome.Template.dto;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;

@Data
public class TemplateSystemDtoInputStatus implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7111035802840375967L;
	private UUID id;
	private boolean enabled;
}
