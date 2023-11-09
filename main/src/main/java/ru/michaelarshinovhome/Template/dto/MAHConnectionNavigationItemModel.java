package ru.michaelarshinovhome.Template.dto;

import lombok.Data;
import java.util.List;

@Data
public class MAHConnectionNavigationItemModel {
	private Integer id;
	private Integer parentId;	
	private boolean isMenuLeaf;
	private String textResourceKey;
	private String menuIcon;
	private String pageUrl;
	private List<MAHConnectionNavigationItemModel> childNodes;	
}
