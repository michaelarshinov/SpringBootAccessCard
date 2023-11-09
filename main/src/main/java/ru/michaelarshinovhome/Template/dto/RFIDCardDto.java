package ru.michaelarshinovhome.Template.dto;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

import lombok.Data;
import ru.michaelarshinovhome.Template.model.RFIDCard;

@Data
public class RFIDCardDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6427530264121824357L;
	private UUID id;
	private Short status;
	private String templateSystemName;
	private UUID templateSystemId;
	private String readableCardNumber;
	private String serialCardNumber;
	private String cardNumberWithoutConversion;
	
	public void setup(RFIDCard card) {
		this.id = card.getId();
		this.status = card.getStatus();
		this.templateSystemName = card.getTemplateSystemName();
		this.templateSystemId = card.getTemplateSystemId();
		this.readableCardNumber = card.getReadableCardNumber();
		this.serialCardNumber= card.getSerialCardNumber();
		this.cardNumberWithoutConversion = card.getCardNumberWithoutConversion();
	}
	public RFIDCardDto(RFIDCard card) {
		if (card == null) return;
		setup(card);
	}
	
	public RFIDCardDto(Optional<RFIDCard> optional) {
		if (optional.isPresent()) {
			setup(optional.get());
		}
	}
}
