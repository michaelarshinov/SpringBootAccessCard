package ru.michaelarshinovhome.Template.dto.wrapped;

import lombok.Data;

@Data
public class WrapperEmpty {
	private boolean success;
	private String message;
	private String snackbarType;
}
