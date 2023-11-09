package ru.michaelarshinovhome.Template.dto.wrapped;

import java.io.Serializable;

import ru.michaelarshinovhome.Template.dto.AccessPointDto;

public class AccessPointDtoOneWrapped extends WrapperOne<AccessPointDto> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5427114710478321932L;
	
	public AccessPointDtoOneWrapped(AccessPointDto accessPointDto) {
		if (accessPointDto == null) {
			this.setSuccess(false);
			this.setMessage("Alert_GetOrCreateAccessPointFromCacheAsync_error");
			this.setSnackbarType("error");
			this.setContent(null);
		} else {
			this.setSuccess(true);
			this.setMessage("Alert_GetOrCreateAccessPointFromCacheAsync_info");
			this.setSnackbarType("info");
			this.setContent(accessPointDto);
		}
	}
}
