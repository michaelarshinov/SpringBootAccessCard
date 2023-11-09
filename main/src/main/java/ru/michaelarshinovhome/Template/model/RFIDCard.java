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
@Table(name="\"RFIDCard\"", uniqueConstraints = 
@UniqueConstraint(columnNames = {"\"ReadableCardNumber\"", "\"SerialCardNumber\""}))
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RFIDCard implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 792159208170883113L;
	
	@Id
	@GeneratedValue 
	@Column(name="\"Id\"", nullable = false)
	private UUID id;
	
	@Column(name="\"Status\"", nullable = false)
	private Short status;

	@Column(name="\"ACSName\"", nullable = false)
	private String accessControlSystemName;
	
	@Column(name="\"ACSId\"", nullable = false)
	private UUID accessControlSystemId;
	
	@Column(name="\"ReadableCardNumber\"", nullable = false)
	private String readableCardNumber;
	
	@Column(name="\"SerialCardNumber\"", nullable = false)
	private String serialCardNumber;
	
	@Column(name="\"CardNumberWithoutConversion\"", nullable = false)
	private String cardNumberWithoutConversion;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"ACSId\"", insertable = false, updatable = false)
	private TemplateSystem templateSystem;
}
