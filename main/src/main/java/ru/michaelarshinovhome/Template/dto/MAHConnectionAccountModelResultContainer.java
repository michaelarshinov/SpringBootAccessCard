package ru.michaelarshinovhome.Template.dto;

import lombok.Data;

@Data
public class MAHConnectionAccountModelResultContainer {
	private boolean success;
	private String message;
	private String snackbarType;
	private MAHConnectionAccountModel content;

}