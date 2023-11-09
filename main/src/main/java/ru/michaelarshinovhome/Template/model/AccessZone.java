package ru.michaelarshinovhome.Template.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data
@Getter
@Setter
/*@Builder
@Entity
@Table(name="\"AccessZone\"", uniqueConstraints = 
@UniqueConstraint(columnNames = {"\"Name\"", "\"ACSId\""}))
@NoArgsConstructor
@AllArgsConstructor
@ToString*/
public class AccessZone implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4338556084000618683L;
/*
	@Id
	@GeneratedValue 
	@Column(name="\"Id\"", nullable = false)
	private UUID id;
	
	@Column(name="\"Name\"", nullable = false)
	private String name;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="accessZone", fetch = FetchType.LAZY)
	private List<AttendanceObject> attendanceObjects;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="accessZone1", fetch = FetchType.LAZY)
	private List<AccessPoint> accessPoints1;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="accessZone2", fetch = FetchType.LAZY)
	private List<AccessPoint> accessPoints2;
	
	@Column(name="\"ACSId\"")
	private UUID acsId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"ACSId\"", insertable = false, updatable = false)
	private TemplateSystem templateSystem;
	*/
}
