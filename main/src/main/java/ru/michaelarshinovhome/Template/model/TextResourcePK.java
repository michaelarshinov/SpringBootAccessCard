package ru.michaelarshinovhome.Template.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

/**
 * The primary key class for the "TextResources" database table.
 * 
 */
@Data
//@Embeddable
public class TextResourcePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="\"ResourceKey\"", insertable=false, updatable=false)
	private String resourceKey;

	@Column(name="\"LanguageId\"", insertable=false, updatable=false)
	private Integer languageId;

}