package ru.michaelarshinovhome.Template.dto.wrapped;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import ru.michaelarshinovhome.Template.dto.DictionaryStaticDto;

@Data
public class DictionaryStaticDtoWrapped extends Wrapper<DictionaryStaticDto> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9157620704586265709L;
			
	public DictionaryStaticDtoWrapped(List<DictionaryStaticDto> dictionariesStaticDto) {
		this.setSuccess(true);
		this.setMessage("Alert_GetOrCreateDictionaryStaticFromCacheAsync_info");
		this.setSnackbarType("info");
		this.setContent(dictionariesStaticDto);
	}

	public DictionaryStaticDtoWrapped(List<DictionaryStaticDto> dictionariesStaticDto, String message) {
		this(dictionariesStaticDto);
		this.setMessage(message);
	}	
		//  "message": "Alert_GetDictionaryStaticNodeAsync_info",

}
