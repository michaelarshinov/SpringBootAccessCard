package ru.michaelarshinovhome.Template.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the "ThemeNames" database table.
 * 
 */
@Data
@Entity
@Table(name="\"ThemeNames\"")
//@NamedQuery(name="ThemeName.findAll", query="SELECT t FROM ThemeName t")
public class ThemeName implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"ThemeId\"")
	private Integer themeId;

	@Column(name="\"LanguageId\"")
	private Integer languageId;

	@Column(name="\"NameResourceKey\"")
	private String nameResourceKey;

	//bi-directional many-to-one association to TextResource
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="\"LanguageId\"", referencedColumnName="\"LanguageId\"", 
				insertable = false, updatable = false),
		@JoinColumn(name="\"NameResourceKey\"",  referencedColumnName="\"ResourceKey\"",
		insertable = false, updatable = false) })
	private TextResource textResource;

	//bi-directional one-to-one association to Theme
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "\"ThemeId\"", referencedColumnName = "\"Id\"")
	private Theme theme;

}