package ru.michaelarshinovhome.Template.dto;

import java.io.Serializable;

import lombok.Data;
import ru.michaelarshinovhome.Template.model.TextResource;

@Data
public class TextResourceDto implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5896362167068896760L;
	private String resourceKey;
    private Integer languageId;
    private String textValue;
    private Integer moduleId;
    
    public TextResourceDto(TextResource textResource, Integer moduleId) {
    	this.resourceKey = textResource.getResourceKey();
    	this.textValue = textResource.getTextValue();
    	this.languageId = textResource.getLanguageId();
    	this.moduleId = moduleId;
    }
}
