package ru.michaelarshinovhome.Template.dto;

import java.util.List;

import lombok.Data;

@Data
public class MAHConnectionAccountModel {
	private Integer accountId;
	private String login;
	private MAHConnectionTokenModel accessToken;
	private MAHConnectionTokenModel refreshToken;
	private Integer typeId;
	private Integer roleId;
	private List<VCRConnectionNavigationItemModel> navigationItems;
	private MAHConnectionPersonModel person;
	private MAHConnectionHostCardModel host;
	private boolean isBlocked;
	private boolean isHost;
	private boolean isCannotBeUpdate;
	private Integer themeId;
	private Integer languageId;
	private String companyGuid;
}
