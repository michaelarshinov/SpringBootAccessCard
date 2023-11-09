package ru.michaelarshinovhome.Template.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import lombok.Builder;
import lombok.Data;


/**
 * The persistent class for the "Log" database table.
 * 
 */
@Data
@Builder
@Entity
@Table(name="\"Log\"")
public class Log implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3863241975266139421L;

	public static final int LEVEL_LENGTH = 20;
	public static final int EXCEPTION_LENGTH = 1000;
	public static final int LOGGER_LENGTH = 200;
	public static final int MESSAGE_LENGTH = 1000;
	
	@Id
	@GeneratedValue(
		    strategy = GenerationType.SEQUENCE,
		    generator = "sequence-generator-log"
		)
		@SequenceGenerator(
		    name = "sequence-generator-log",
		    sequenceName = "\"Log_Id_seq\"",
		    allocationSize = 1
		)
	@Column(name="\"Id\"")
	private Integer id;

	@Column(name="\"Date\"")
	private Timestamp date;
	
	@Column(name="\"Level\"", length = LEVEL_LENGTH)
	private String level;

	@Column(name="\"MethodName\"")
	private String methodName;
	
	@Column(name="\"Exception\"", length = EXCEPTION_LENGTH)
	private String exception;

	@Column(name="\"ExecTimeInMilliseconds\"")
	private Integer execTimeInMilliseconds;

	@Column(name="\"Logger\"", length = LOGGER_LENGTH)
	private String logger;

	@Column(name="\"Message\"", length = MESSAGE_LENGTH)
	private String message;

	@Column(name="\"Meta\"")
	private String meta;

}