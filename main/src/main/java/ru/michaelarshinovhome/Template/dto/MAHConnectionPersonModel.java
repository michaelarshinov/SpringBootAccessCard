package ru.michaelarshinovhome.Template.dto;

import lombok.Data;

@Data
public class MAHConnectionPersonModel {
	private Integer id;
	private Integer accountId;
	private String startDateTime;
	private String endDateTime;
	private VCRConnectionPersonalDataModel personalData;
	private Boolean isHost;
	private Boolean isAccount;
}
