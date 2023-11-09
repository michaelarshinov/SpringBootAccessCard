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
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@Entity
@Table(name="\"AttendanceObject\"", uniqueConstraints = 
@UniqueConstraint(columnNames = {"\"Name\"", "\"Path\"", "\"ACSId\""}))
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AttendanceObject implements Serializable {
	
	/** 
	 * 
	 */
	private static final long serialVersionUID = 2929717354718332886L;
	
	@Id
	@GeneratedValue 
	@Column(name="\"Id\"", nullable = false)
	private UUID id;
	
	@Column(name="\"Name\"", nullable = false)
	private String name;
	
	@Column(name="\"ACSId\"", nullable = false)
	private UUID acsId;
	
	@Column(name="\"Path\"", nullable = false)
	private String path;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"ACSId\"", insertable = false, updatable = false)
	private TemplateSystem templateSystem;
	
}
