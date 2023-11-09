package ru.michaelarshinovhome.Template.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import lombok.Data;
import ru.michaelarshinovhome.Template.model.AccessEvent;

@Data
public class AccessEventDto implements DtoDateTimeFormatted, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8473711362424459602L;
	
	private UUID id;
	private Integer accessIdType;
	private String accessId;
	private UUID acsId;
	private Timestamp date;
	private UUID accessPointId;
	private String dateFormatted;
	private String accessPointName;
	private String templateSystemName;
	private String accessIdTypeName;
	
	public AccessEventDto(AccessEvent accessEvent) {
		this.id = accessEvent.getId();
		this.accessIdType = accessEvent.getAccessIdType();
		this.accessId = accessEvent.getAccessId();
		this.acsId = accessEvent.getAcsId();
		this.date = accessEvent.getDate();
		this.accessPointId = accessEvent.getAccessPointId();
		this.dateFormatted = formatDate(accessEvent.getDate()) + " " + formatTime(accessEvent.getDate());
		// accessIdTypeName устанавливается снаружи, по languageId
		this.templateSystemName = accessEvent.getTemplateSystem() != null ? accessEvent.getAccessControlSystem().getName() : "";
		this.accessPointName = accessEvent.getAccessPoint() != null ? accessEvent.getAccessPoint().getName() : "";
	}
}
