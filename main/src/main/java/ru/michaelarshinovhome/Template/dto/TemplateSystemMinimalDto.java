package ru.michaelarshinovhome.Template.dto;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.Data;
import ru.michaelarshinovhome.Template.model.TemplateSystem;

@Data
public class TemplateSystemMinimalDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1026528940732751099L;
	private UUID id;
	private String name;
	private String version;
	private boolean enabled;
	private String logo;
	//private List<AccessPointDto> accessPoints;
	private String brand;
	
	public TemplateSystemMinimalDto(TemplateSystem templateSystem) {
		this.id = templateSystem.getId();
		this.name = templateSystem.getName();
		this.version = templateSystem.getVersion();
		this.enabled = templateSystem.isEnabled();
		//this.accessPoints = templateSystem.getAccessPoints().stream()
		//	.map(x-> new AccessPointDto(x)).collect(Collectors.toList());
		this.logo = templateSystem.getLogo();
		this.brand = templateSystem.getBrand();
	}
}
