package ru.michaelarshinovhome.Template.dto;

import java.io.Serializable;

//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.michaelarshinovhome.Template.model.Language;

@Data
public class LanguageDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1758960741896298536L;
	//@ApiModelProperty(notes = "Language ID", example = "1", required = true) 
	private Integer id;
	//@ApiModelProperty(notes = "Culture", example = "EN-en", required = true) 
	private String culture;
	//@ApiModelProperty(notes = "Languages", example = "Еnglish", required = true) 
	private String languages;
	//private List<TextResource> textResources;
	public LanguageDto(Language language) {
		this.id = language.getId();
		this.culture = language.getCulture();
		this.languages = language.getLanguages();
		//this.textResources = new ArrayList<>();
	}
}
