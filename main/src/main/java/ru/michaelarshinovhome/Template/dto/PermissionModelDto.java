package ru.michaelarshinovhome.Template.dto;

import java.io.Serializable;

import lombok.Data;
import ru.michaelarshinovhome.Template.model.Permission;

@Data
public class PermissionModelDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7609661919380476963L;
	public PermissionModelDto(Permission x) {
		this.id = x.getId();
		this.name = x.getName();
	}
	private Integer id;
	private String name;
}
