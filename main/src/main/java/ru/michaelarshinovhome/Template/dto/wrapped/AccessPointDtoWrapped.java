package ru.michaelarshinovhome.Template.dto.wrapped;

import java.io.Serializable;
import java.util.List;

import ru.michaelarshinovhome.Template.dto.AccessPointDto;

public class AccessPointDtoWrapped extends Wrapper<AccessPointDto> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1499401643922760033L;
	public AccessPointDtoWrapped(List<AccessPointDto> accessPointsDto) {
		this.setSuccess(true);
		this.setMessage("Alert_GetOrCreateAccessPointFromCacheAsync_info");
		this.setSnackbarType("info");
		this.setContent(accessPointsDto);
	}
}
