package ru.michaelarshinovhome.Template.dto.wrapped;

import java.io.Serializable;
import java.util.List;

import ru.michaelarshinovhome.Template.dto.RFIDCardDto;

public class RFIDCardDtoWrapped extends Wrapper<RFIDCardDto> implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1279077550616560087L;

	public RFIDCardDtoWrapped(List<RFIDCardDto> cardDto) {
		this.setSuccess(true);
		this.setMessage("Alert_GetOrCreateCardFromCacheAsync_info");
		this.setSnackbarType("info");
		this.setContent(cardDto);
	}
}
