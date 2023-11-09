package ru.michaelarshinovhome.Template.dto.wrapped;

import java.io.Serializable;

import lombok.Data;

@Data
public class ThemeDtoWrapped implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2327493264783233097L;
	private ThemeDtoCollectionWrapped content;
	private boolean success = true;
	private String message =  "Alert_GetThemesAsync_info";
	private String snackbarType = "info";
	public ThemeDtoWrapped(ThemeDtoCollectionWrapped themeDtoCollectionWrapped) {
		content = themeDtoCollectionWrapped;
	}
}
