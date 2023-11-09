package ru.michaelarshinovhome.Template.dto.wrapped;

import java.io.Serializable;
import java.util.List;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.michaelarshinovhome.Template.dto.LanguageDto;

@Data
public class LanguageDtoWrapped extends Wrapper<LanguageDto> implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6186273469564546810L;
	public  LanguageDtoWrapped(List<LanguageDto> languagesDto) {
		this.setContent(languagesDto);
		this.setSuccess(true);
		this.setMessage("Alert_GetLanguagesAsync_info");
		this.setSnackbarType("info");
	}
}
