package ru.michaelarshinovhome.Template.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;


/**
 * The persistent class for the "TextResources" database table.
 * 
 */
@Data
@Entity
@IdClass(TextResourcePK.class)
@Table(name="\"TextResources\"")
@NamedQuery(name="TextResource.findAll", query="SELECT t FROM TextResource t")
public class TextResource implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * @EmbeddedId private TextResourcePK id;
	 */
	
	@Id
	@Column(name="\"ResourceKey\"")
	private String resourceKey;

	@Id
	@Column(name="\"LanguageId\"", insertable=false, updatable=false)
	private Integer languageId;
	
	
	@Column(name="\"TextValue\"")
	private String textValue;

	//bi-directional many-to-one association to NavigationItem
	@OneToMany(mappedBy="textResource", fetch=FetchType.LAZY)
	private List<NavigationItem> navigationItems;

	
	//bi-directional many-to-one association to Language
	@ManyToOne
	@JoinColumn(name= "\"LanguageId\"")
	private Language language;
    

	//bi-directional many-to-one association to ThemeName
	@OneToMany(mappedBy="textResource")
	private List<ThemeName> themeNames;

	//bi-directional many-to-one association to DictionaryStatic
	@OneToMany(mappedBy="textResource")
	private List<DictionaryStatic> dictionaryStatics;

}