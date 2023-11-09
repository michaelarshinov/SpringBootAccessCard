package ru.michaelarshinovhome.Template.dto.wrapped;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import ru.michaelarshinovhome.Template.dto.TextResourceDto;

@Data
public class TextResourceDtoWrapped extends Wrapper<TextResourceDto> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 489782527802208180L;
	public  TextResourceDtoWrapped(List<TextResourceDto> textResourcesDto) {
		this.setContent(textResourcesDto);
		this.setSuccess(true);
		this.setMessage("Alert_GetAutoriseTextResource_info");
		this.setSnackbarType("info");
	}
	
}
