package ru.michaelarshinovhome.Template.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccessIdTypeDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 894421807556566329L;
	Integer id;
	String name;
	public AccessIdTypeDto(int id, String textValue) {
		this.id = id;
		this.name = textValue;
	}
}
