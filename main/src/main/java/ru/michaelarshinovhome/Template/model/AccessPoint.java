package ru.michaelarshinovhome.Template.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table(name="\"AccessPoint\"", uniqueConstraints = 
@UniqueConstraint(columnNames = {"\"AttendanceObjectForExitId\"", "\"AttendanceObjectForEntranceId\""}))
@NoArgsConstructor
@AllArgsConstructor
public class AccessPoint implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4637023816066475005L;
	@Id
	@GeneratedValue 
	@Column(name="\"Id\"", nullable = false)
	private UUID id;
	
	@Column(name="\"Name\"", nullable = false)	
	private String name;
	
	/*@Column(name="\"AccessZone1Id\"")
	private UUID accessZone1Id;
	
	@Column(name="\"AccessZone2Id\"")
	private UUID accessZone2Id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"AccessZone1Id\"", insertable = false, updatable = false)
	private AccessZone accessZone1;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"AccessZone2Id\"", insertable = false, updatable = false)
	private AccessZone accessZone2;*/
	
	@Column(name="\"AttendanceObjectForExitId\"")
	private UUID attendanceObjectForExitId;
	
	@Column(name="\"AttendanceObjectForEntranceId\"")
	private UUID attendanceObjectForEntranceId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"AttendanceObjectForExitId\"", insertable = false, updatable = false)
	private AttendanceObject attendanceObjectForExit;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"AttendanceObjectForEntranceId\"", insertable = false, updatable = false)
	private AttendanceObject attendanceObjectForEntrance;

	@Column(name="\"ACSId\"", nullable = false)
	private UUID acsId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"ACSId\"", insertable = false, updatable = false)
	private TemplateSystem templateSystem;
}
