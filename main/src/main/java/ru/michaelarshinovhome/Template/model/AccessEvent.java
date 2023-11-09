package ru.michaelarshinovhome.Template.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the "TemplateSystem" database table.
 * 
 */
@Data
@Builder(toBuilder = true)
@Entity
@Table(name="\"AccessEvent\""/*, uniqueConstraints = 
	@UniqueConstraint(columnNames = {"\"Name\"", "\"Version\""})*/)
@NoArgsConstructor
@AllArgsConstructor
public class AccessEvent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1420196856484886923L;
	@Id
	@GeneratedValue 
	@Column(name="\"Id\"", nullable = false)
	private UUID id;
	
	@Column(name="\"AccessIdType\"", nullable = false)
	private Integer accessIdType;
	
	@Column(name="\"AccessId\"", nullable = false)
	private String accessId;
	
	@Column(name="\"ACSId\"", nullable = false)
	private UUID acsId;
	
	@Column(name="\"Date\"", nullable = false)
	private Timestamp date;
	
	@Column(name="\"AccessPointId\"", nullable = false)
	private UUID accessPointId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"ACSId\"", insertable = false, updatable = false)
	private TemplateSystem templateSystem;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"AccessPointId\"", insertable = false, updatable = false)
	private AccessPoint accessPoint;
	
}
