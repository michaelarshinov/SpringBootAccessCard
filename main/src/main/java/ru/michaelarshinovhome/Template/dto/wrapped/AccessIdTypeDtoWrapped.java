package ru.michaelarshinovhome.Template.dto.wrapped;

import java.io.Serializable;
import java.util.List;

import ru.michaelarshinovhome.Template.dto.AccessIdTypeDto;
import ru.michaelarshinovhome.Template.dto.RFIDCardDto;

public class AccessIdTypeDtoWrapped extends Wrapper<AccessIdTypeDto> implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3420614590152176449L;

	public AccessIdTypeDtoWrapped(List<AccessIdTypeDto> accessIdTypeDto) {
		this.setSuccess(true);
		this.setMessage("Alert_GetOrCreateAccessIdTypeFromCacheAsync_info");
		this.setSnackbarType("info");
		this.setContent(accessIdTypeDto);
	}
}
