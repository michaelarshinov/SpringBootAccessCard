package ru.michaelarshinovhome.Template.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PermissionNavigationItemModelDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -272538598135366340L;
	public PermissionNavigationItemModelDto(Integer id, Integer id2) {
		this.permissionId = id;
		this.navigationItemId = id2;
	}
	private Integer permissionId;
	private Integer navigationItemId;
}
