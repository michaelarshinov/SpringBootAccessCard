package ru.michaelarshinovhome.Template.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MAHConnectionLogin {
	private String login;
	private String password;
}
