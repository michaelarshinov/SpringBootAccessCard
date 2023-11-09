package ru.michaelarshinovhome.Template.dto.wrapped;

import java.io.Serializable;

import lombok.Data;
import ru.michaelarshinovhome.Template.dto.AccessControlSystemDto;

@Data
public class TemplateSystemDtoOneWrapped extends WrapperOne<TemplateSystemDto> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8470133595808608749L;

	
	public TemplateSystemDtoOneWrapped(TemplateSystemDto templateSystemDto) {
		if (accessControlSystemDto == null) {
			this.setSuccess(false);
			this.setMessage("Alert_GetOrCreateTemplateSystemFromCacheAsync_error");
			this.setSnackbarType("error");
			this.setContent(null);
		} else {
			this.setSuccess(true);
			this.setMessage("Alert_GetOrCreateTemplateSystemFromCacheAsync_info");
			this.setSnackbarType("info");
			this.setContent(templateSystemDto);
		}
	}
}
