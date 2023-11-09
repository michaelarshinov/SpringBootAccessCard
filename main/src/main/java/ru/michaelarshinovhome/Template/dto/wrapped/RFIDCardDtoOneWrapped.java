package ru.michaelarshinovhome.Template.dto.wrapped;

import java.io.Serializable;

import ru.michaelarshinovhome.Template.dto.RFIDCardDto;

public class RFIDCardDtoOneWrapped extends WrapperOne<RFIDCardDto> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2780441035617301977L;

	public RFIDCardDtoOneWrapped(RFIDCardDto cardDto) {
		if (cardDto == null) {
			this.setSuccess(false);
			this.setMessage("Alert_GetOrCreateCardCacheAsync_error");
			this.setSnackbarType("error");
			this.setContent(null);
		} else {
			this.setSuccess(true);
			this.setMessage("Alert_GetOrCreateCardFromCacheAsync_info");
			this.setSnackbarType("info");
			this.setContent(cardDto);
		}
	}
}
