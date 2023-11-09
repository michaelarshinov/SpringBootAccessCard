package ru.michaelarshinovhome.Template.dto;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;

@Data
public class RFIDCardDtoInput implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 202775512892264912L;
	//private UUID id;
	private Short status;
	//private String templateSystemName;
	private UUID templateSystemId;
	private String readableCardNumber;
	private String serialCardNumber;
	private String cardNumberWithoutConversion;
}
