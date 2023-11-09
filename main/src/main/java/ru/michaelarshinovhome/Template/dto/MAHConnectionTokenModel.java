package ru.michaelarshinovhome.Template.dto;

import lombok.Data;

@Data
public class MAHConnectionTokenModel {
	private String token;
	private Integer expirationTime;
}
