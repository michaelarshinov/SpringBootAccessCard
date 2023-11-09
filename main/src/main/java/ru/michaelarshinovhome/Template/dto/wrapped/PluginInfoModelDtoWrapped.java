package ru.michaelarshinovhome.Template.dto.wrapped;

import java.io.Serializable;

import lombok.Data;
import ru.michaelarshinovhome.Template.dto.PluginInfoModel;

@Data
public class PluginInfoModelDtoWrapped implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1733035777481253191L;
	private PluginInfoModel content;
	private boolean success;
	private String message;
	private String snackbarType;
	public PluginInfoModelDtoWrapped(PluginInfoModel info) {
		this.content = info;
		this.success = true;
	}
	public PluginInfoModelDtoWrapped(boolean success, String message) {
		this.success = success;
		this.message = message;
		if (!success) {
			this.snackbarType = "error";
			
		}
	}
}
