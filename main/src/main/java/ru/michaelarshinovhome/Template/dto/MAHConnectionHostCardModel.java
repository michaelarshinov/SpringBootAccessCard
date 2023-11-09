package ru.michaelarshinovhome.Template.dto;

import java.util.List;

import lombok.Data;

@Data
public class MAHConnectionHostCardModel {
	private Integer id;
	private String number;
	private Integer typeId;
	private String startDate;
	private String endDate;
	private String landlinePhone;
	private String cellularPhone;
	private String eMail;
	private	MAHConnectionPersonModel person;
	private List<MAHConnectionPlaceShortModel> places;
	private Boolean isCannotBeUpdate;
	private String organizationName;
	private Integer organizationTypeId;
	private Integer organizationId;
}
