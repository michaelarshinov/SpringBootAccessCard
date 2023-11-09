package ru.michaelarshinovhome.Template.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the "AccessControlSystem" database table.
 * 
 */
@Data
@Builder(toBuilder = true)
@Entity
@Table(name="\"TemplateSystem\"", uniqueConstraints = 
	@UniqueConstraint(columnNames = {"\"Name\"", "\"Version\""}))
@NoArgsConstructor
@AllArgsConstructor
public class TemplateSystem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3815523640155534090L;
	
	@Id
	@GeneratedValue 
	@Column(name="\"Id\"", nullable = false)
	private UUID id;

	@Column(name="\"Name\"", nullable = false)
	private String name;

	@Column(name="\"Version\"", nullable = false)
	private String version;
	
	@Column(name="\"Enabled\"")
	private boolean enabled;
	
	@JsonIgnore
	@OneToMany(mappedBy="accessControlSystem", fetch = FetchType.LAZY)
	private List<AccessPoint> accessPoints;
	
	/*@JsonIgnore
	@OneToMany(mappedBy="accessControlSystem", fetch = FetchType.LAZY)
	private List<AccessZone> accessZones;*/
	
	@JsonIgnore
	@OneToMany(mappedBy="accessControlSystem", fetch = FetchType.LAZY)
	private List<AttendanceObject> attendanceObjects;
	
	@JsonIgnore
	@OneToMany(mappedBy="accessControlSystem", fetch = FetchType.LAZY)
	private List<RFIDCard> cards;
	
	@Column(name="\"Logo\"", nullable = true)
	private String logo;
	
	@Column(name="\"Brand\"", nullable = true)
	private String brand;
}