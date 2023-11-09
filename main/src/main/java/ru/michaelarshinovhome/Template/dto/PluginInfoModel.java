package ru.michaelarshinovhome.Template.dto;

import java.util.List;

import lombok.Data;

@Data
public class PluginInfoModel {
	private List<TextResourceDto> textResources;
	private List<NavigationItemModelDto> navigationItems;
	private List<PermissionModelDto> permissions;
	private List<PermissionNavigationItemModelDto> permissionNavigationItems;
	private String guid;
	private String name;
	private String refreshDateTime;
	private Integer refreshStatus;
	private Integer accountRoleAccess;
	private String url;
}
