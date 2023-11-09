package ru.michaelarshinovhome.Template.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the "permission_navigationItem" database table.
 * 
 */
@Data
@Embeddable
@Table(name="\"permission_navigationItem\"")
//@NamedQuery(name="Permission_navigationItem.findAll", query="SELECT p FROM Permission_navigationItem p")
public class Permission_navigationItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="\"NavigationItemId\"")
	private Integer navigationItemId;

	@Column(name="\"PermissionId\"")
	private Integer permissionId;

}