package ru.michaelarshinovhome.Template.dto.wrapped;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import ru.michaelarshinovhome.Template.dto.TemplateSystemDto;
import ru.michaelarshinovhome.Template.dto.AccessEventDto;

@Data
public class AccessEventDtoWrapped extends Wrapper<AccessEventDto> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2542518881555034239L;

	public AccessEventDtoWrapped(List<AccessEventDto> accessEventDto) {
		this.setSuccess(true);
		this.setMessage("Alert_GetOrCreateAccessEventFromCacheAsync_info");
		this.setSnackbarType("info");
		this.setContent(accessEventDto);
	}
}
