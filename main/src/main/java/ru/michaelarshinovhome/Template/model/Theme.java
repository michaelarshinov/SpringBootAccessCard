package ru.michaelarshinovhome.Template.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the "Theme" database table.
 * 
 */
@Data
@Entity
@Table(name="\"Theme\"")
//@NamedQuery(name="Theme.findAll", query="SELECT t FROM Theme t")
public class Theme implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"Id\"")
	private Integer id;

	@Column(name="\"ThemeValue\"")
	private String themeValue;

	//bi-directional one-to-one association to ThemeName
	@OneToOne(mappedBy="theme", fetch=FetchType.LAZY)
	private ThemeName themeName;

}