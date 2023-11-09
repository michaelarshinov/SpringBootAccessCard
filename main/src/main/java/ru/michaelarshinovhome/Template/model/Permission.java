package ru.michaelarshinovhome.Template.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import lombok.Data;


/**
 * The persistent class for the "Permission" database table.
 * 
 */
@Data
@Entity
@Table(name="\"Permission\"")
//@NamedQuery(name="Permission.findAll", query="SELECT p FROM Permission p")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(
		    strategy = GenerationType.SEQUENCE,
		    generator = "sequence-generator-permission"
		)
		@SequenceGenerator(
		    name = "sequence-generator-permission",
		    sequenceName = "Permission_Id_seq",
		    allocationSize = 1
		)
	@Column(name="\"Id\"")
	private Integer id;

	@Column(name="\"Name\"")
	private String name;
	
	@ManyToMany
	/*@JoinTable(
		name="\"permission_navigationItem\""
		, joinColumns={

			}
		, inverseJoinColumns={

			}
		)*/
	@JoinTable(
	  name = "\"permission_navigationItem\"", 
	  joinColumns = @JoinColumn(name = "\"PermissionId\""), 
	  inverseJoinColumns = @JoinColumn(name = "\"NavigationItemId\""))
	private List<NavigationItem> navigationItems;

}