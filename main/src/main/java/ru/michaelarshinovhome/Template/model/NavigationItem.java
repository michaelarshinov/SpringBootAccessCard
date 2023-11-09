package ru.michaelarshinovhome.Template.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import java.util.List;


/**
 * The persistent class for the "NavigationItem" database table.
 * 
 */
@Data
@Entity
@Table(name="\"NavigationItem\"")
@NamedQuery(name="NavigationItem.findAll", query="SELECT n FROM NavigationItem n")
public class NavigationItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(
		    strategy = GenerationType.SEQUENCE,
		    generator = "sequence-generator-ni"
		)
		@SequenceGenerator(
		    name = "sequence-generator-ni",
		    sequenceName = "NavigationItem_Id_seq",
		    allocationSize = 1
		)
	@Column(name="\"Id\"")
	private Integer id;

	@Column(name="\"IsMenuLeaf\"")
	private Boolean isMenuLeaf;

	@Column(name="\"LanguageId\"")
	private Integer languageId;

	@Column(name="\"MenuIcon\"")
	private String menuIcon;

	@Column(name="\"PageUrl\"")
	private String pageUrl;

	@Column(name="\"ParentId\"")
	private Integer parentId;

	@Column(name="\"TextResourceKey\"")
	private String textResourceKey;

	//bi-directional many-to-one association to NavigationItem
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"Id\"", insertable = false, updatable = false)
	private NavigationItem navigationItem;

	//bi-directional many-to-one association to NavigationItem
	@OneToMany(mappedBy="navigationItem")
	private List<NavigationItem> navigationItems;

	//bi-directional many-to-one association to TextResource
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="\"LanguageId\"", referencedColumnName="\"LanguageId\"", 
				insertable = false, updatable = false),
		@JoinColumn(name="\"TextResourceKey\"",  referencedColumnName="\"ResourceKey\"",
		insertable = false, updatable = false) })
	private TextResource textResource;
	
	//bi-directional many-to-many association to Permission
	@ManyToMany(mappedBy="navigationItems")
	private List<Permission> permissions;

}