package ru.michaelarshinovhome.Template.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.List;


/**
 * The persistent class for the "dictionaryStatic" database table.
 * 
 */
@Data
@Entity
@Table(name="\"DictionaryStatic\"")
//@NamedQuery(name="DictionaryStatic.findAll", query="SELECT d FROM DictionaryStatic d")
public class DictionaryStatic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(
		    strategy = GenerationType.SEQUENCE,
		    generator = "sequence-generator"
		)
		@SequenceGenerator(
		    name = "sequence-generator",
		    sequenceName = "DictionaryStatic_Id_seq",
		    allocationSize = 1
		)
	@Column(name="\"Id\"")
	private Integer id;

	@Column(name="\"ImageValue\"")
	private byte[] imageValue;

	@Column(name="\"LanguageId\"")
	private Integer languageId;

	@Column(name="\"MaterialUIIconName\"")
	private String materialUIIconName;

	@Column(name="\"Name\"")
	private String name;

	@Column(name="\"ParentId\"")
	private Integer parentId;

	@Column(name="\"TextResourceKey\"")
	private String textResourceKey;

	//bi-directional many-to-one association to TextResource
	@ManyToOne(fetch=FetchType.LAZY)
	/*@JoinColumns({@JoinColumn(name="\"ResourceKey\"", insertable = false, updatable = false),
		@JoinColumn(name="\"LanguageId\"", insertable = false, updatable = false)})
		*/
	@JoinColumns({
		@JoinColumn(name="\"LanguageId\"", referencedColumnName="\"LanguageId\"", 
				insertable = false, updatable = false),
		@JoinColumn(name="\"TextResourceKey\"",  referencedColumnName="\"ResourceKey\"",
		insertable = false, updatable = false) })
	private TextResource textResource;

	//bi-directional many-to-one association to DictionaryStatic
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"Id\"", insertable = false, updatable = false)
	private DictionaryStatic dictionaryStatic;

	//bi-directional many-to-one association to DictionaryStatic
	@OneToMany(mappedBy="dictionaryStatic")
	private List<DictionaryStatic> dictionaryStatics;

}