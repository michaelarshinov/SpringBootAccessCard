package ru.michaelarshinovhome.Template.dto;

import java.io.Serializable;

import lombok.Data;
import ru.michaelarshinovhome.Template.model.DictionaryStatic;

@Data
public class DictionaryStaticDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7924060160711358092L;
	
    private Integer id;
    private String name;
    private String textResourceKey;
    private String iconName;
    private byte[] imageValue;
    private Integer parentId;
    private Integer languageId;
    
	public DictionaryStaticDto(DictionaryStatic dictionaryStatic) {
		this.id = dictionaryStatic.getId();
		this.name = dictionaryStatic.getName();
		this.textResourceKey = dictionaryStatic.getTextResourceKey();
		this.iconName = dictionaryStatic.getMaterialUIIconName();
		this.imageValue = dictionaryStatic.getImageValue();
		this.parentId = dictionaryStatic.getParentId();
		this.languageId = dictionaryStatic.getLanguageId();
	}
}
