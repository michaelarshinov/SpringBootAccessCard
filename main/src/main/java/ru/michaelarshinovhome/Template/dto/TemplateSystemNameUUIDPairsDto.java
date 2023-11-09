package ru.michaelarshinovhome.Template.dto;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import ru.michaelarshinovhome.Template.model.TemplateSystem;

@Data
public class TemplateSystemNameUUIDPairsDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1010658490919492654L;
	private UUID id;
	private String name;
	//private String version;
	//private boolean enabled;
	//private List<AccessPointDto> accessPoints;
	//private String logo;
	//private String brand;
	
	public TemplateSystemNameUUIDPairsDto(TemplateSystem templateSystem) {
		this.id = templateSystem.getId();
		this.name = templateSystem.getName();
		//this.version = templateSystem.getVersion();
		//this.enabled = templateSystem.isEnabled();
		//this.accessPoints = templateSystem.getAccessPoints().stream()
			//.map(x-> new AccessPointDto(x)).collect(Collectors.toList());
	}
}
