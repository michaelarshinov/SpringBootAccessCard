package ru.michaelarshinovhome.Template.dto.wrapped;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import ru.michaelarshinovhome.Template.dto.TemplateSystemDto;

@Data
public class TemplateSystemDtoWrapped extends Wrapper<TemplateSystemDto> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8470133595808608749L;

	
	public TemplateSystemDtoWrapped(List<TemplateSystemDto> templateSystemsDto) {
		this.setSuccess(true);
		this.setMessage("Alert_GetOrCreateTemplateSystemFromCacheAsync_info");
		this.setSnackbarType("info");
		this.setContent(templateSystemsDto);
	}
}
