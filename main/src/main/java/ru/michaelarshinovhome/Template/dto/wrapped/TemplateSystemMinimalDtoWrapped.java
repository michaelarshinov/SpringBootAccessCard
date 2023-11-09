package ru.michaelarshinovhome.Template.dto.wrapped;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import ru.michaelarshinovhome.Template.dto.TemplateSystemDto;
import ru.michaelarshinovhome.Template.dto.TemplateSystemMinimalDto;

@Data
public class TemplateSystemMinimalDtoWrapped extends Wrapper<TemplateSystemMinimalDto> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8470133595808608749L;

	
	public TemplateSystemMinimalDtoWrapped(List<TemplateSystemMinimalDto> templateSystemsDto) {
		this.setSuccess(true);
		this.setMessage("Alert_GetOrCreateTemplateSystemFromCacheAsync_info");
		this.setSnackbarType("info");
		this.setContent(templateSystemsDto);
	}
}
