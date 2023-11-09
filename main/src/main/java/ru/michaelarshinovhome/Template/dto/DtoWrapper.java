package ru.michaelarshinovhome.Template.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class DtoWrapper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3662960091238425236L;
	private boolean success;
	private String message;
	private String snackbarType;
}
