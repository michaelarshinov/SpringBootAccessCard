package ru.michaelarshinovhome.Template.dto.wrapped;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import ru.michaelarshinovhome.Template.dto.ThemeDto;

@Data
public class ThemeDtoCollectionWrapped implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6007282117456347218L;
	List<ThemeDto> collectionLoaded;
	List<ThemeDto> collectionKeys;
}
