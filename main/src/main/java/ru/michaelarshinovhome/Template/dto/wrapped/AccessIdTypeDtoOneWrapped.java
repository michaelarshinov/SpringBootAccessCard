package ru.michaelarshinovhome.Template.dto.wrapped;

import java.io.Serializable;

import ru.michaelarshinovhome.Template.dto.AccessIdTypeDto;

public class AccessIdTypeDtoOneWrapped extends WrapperOne<AccessIdTypeDto> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -522263714941589556L;

	public AccessIdTypeDtoOneWrapped(AccessIdTypeDto accessIdTypeDto) {
		if (accessIdTypeDto == null) {
			this.setSuccess(false);
			this.setMessage("Alert_GetOrCreateAccessIdTypeFromCacheAsync_error");
			this.setSnackbarType("error");
			this.setContent(null);
		} else {
			this.setSuccess(true);
			this.setMessage("Alert_GetOrCreateAccessIdTypeFromCacheAsync_info");
			this.setSnackbarType("info");
			this.setContent(accessIdTypeDto);
		}
	}
}
