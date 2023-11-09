package ru.michaelarshinovhome.Template.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;


/**
 * The persistent class for the "Language" database table.
 * 
 */
@Data
@Entity
@Table(name="\"Language\"")
//@NamedQuery(name="Language.findAll", query="SELECT l FROM Language l")
public class Language implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(
		    strategy = GenerationType.SEQUENCE,
		    generator = "sequence-generator-lang"
		)
		@SequenceGenerator(
		    name = "sequence-generator-lang",
		    sequenceName = "Language_Id_seq",
		    allocationSize = 1
		)
	@Column(name="\"Id\"")
	private Integer id;

	@Column(name="\"Culture\"")
	private String culture;

	@Column(name="\"Languages\"")
	private String languages;

	
	//bi-directional many-to-one association to TextResource
	@OneToMany(mappedBy="language", fetch=FetchType.LAZY)
	private List<TextResource> textResources;
	

}