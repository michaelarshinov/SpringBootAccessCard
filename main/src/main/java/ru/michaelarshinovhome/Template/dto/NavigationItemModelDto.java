package ru.michaelarshinovhome.Template.dto;

import java.io.Serializable;

import lombok.Data;
import ru.michaelarshinovhome.Template.model.NavigationItem;

@Data
public class NavigationItemModelDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7055172244506790032L;
	public NavigationItemModelDto(NavigationItem x) {
		this.id = x.getId();
		this.isMenuLeaf = x.getIsMenuLeaf();
		this.parentId = x.getParentId();
		this.textResourceKey = x.getTextResourceKey();
		this.languageId = x.getLanguageId();
		this.menuIcon = x.getMenuIcon();
		this.pageUrl = x.getPageUrl();
	}
	private Integer id;
	private Integer parentId;
	private boolean isMenuLeaf;
	private String textResourceKey;
	private Integer languageId;
	private String menuIcon;
	private String pageUrl;
}
