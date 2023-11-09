package ru.michaelarshinovhome.Template.dto;

import lombok.Data;

@Data
public class MAHConnectionPersonalDataModel {
	private Integer id;
	private Integer personId;
	private Integer personalDataSourceId;
	private String personalDataSourceName;
	private String dateOfIssue;
	private Boolean isActual;
	private String firstName;
	private String middleName;
	private String lastName;
	private Integer genderId;
	private String genderName;
	private String birthDate;
	private String cellularPhone;
	private String eMail;
}
