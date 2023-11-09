package ru.michaelarshinovhome.Template.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties
public class ThemeDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6159150832563122274L;
	@JsonProperty private Integer id;
	@JsonProperty private String themeName;
	@JsonProperty private String background;
	@JsonProperty private String logo;
	@JsonProperty private String login1;
	@JsonProperty private String login2;
	@JsonProperty private String login3;
	@JsonProperty private String login4;
	@JsonProperty private String login5;
	@JsonProperty private String mainElementBackgroudColor;
	@JsonProperty private String loginbackgroundPanel;
	@JsonProperty private String mainElementTextColor;
	@JsonProperty private String loginPosition;
	@JsonProperty private String errorTextColor;
	@JsonProperty private String backgroundColor;
	@JsonProperty private String alternativeElementBackgroudColor;
	@JsonProperty private String backgroundBottomColor;
	@JsonProperty private String alternativeElementTextColor;
	@JsonProperty private String colorMenu;
	@JsonProperty private String cancelElementBackgroudColorFont;	
	@JsonProperty private String mainElementBackgroudColorFont;
	@JsonProperty private String shadowTopPanel;
	@JsonProperty private String paperColor;
	@JsonProperty private String hoverMenuElementColor;
	@JsonProperty private String backgroundTable;
	@JsonProperty private String borderTableColor;
	@JsonProperty private String listBackgroundColor;
	@JsonProperty private String textPrimary;
	@JsonProperty private String textSecondary;
	@JsonProperty private String textTertiary;
	@JsonProperty private String fillPrimary;
	@JsonProperty private String fillSecondary;
	@JsonProperty private String fillTertiary;
	@JsonProperty private String strokePrimary;
	@JsonProperty private String strokeSecondary;
	@JsonProperty private String backgroundPrimary;
	@JsonProperty private String backgroundPrimaryBorder;	
	@JsonProperty private String backgroundSecondary;
	@JsonProperty private String backgroundSecondaryBorder;
	@JsonProperty private String buttonActive;	
	@JsonProperty private String buttonHover;
	@JsonProperty private String buttonInactive;
	@JsonProperty() private String mAdailyTemplateInside;
	@JsonProperty private String mAdailyTemplateOutside;	
	@JsonProperty private String mAdailyTemplateMissing;	
	@JsonProperty private String mAdailyTemplatePresent;	
	@JsonProperty private String mAperiodTemplateInside;
	@JsonProperty private String mAperiodTemplateOutside;	
	@JsonProperty private String mAamountPeople1;
	@JsonProperty private String mAamountPeople2;
	@JsonProperty private String mAamountPeople3;
	@JsonProperty private String mAamountPeople4;
	@JsonProperty private String mAamountPeople5;
	@JsonProperty private String mAamountPeople6;
	@JsonProperty private String mAamountPeople7;
	@JsonProperty private String mAamountPeople8;
	@JsonProperty private String outsideMobileNone;
	@JsonProperty private String buttonInactiveHover;
	@JsonProperty private String iconFill;
	@JsonProperty private String button2Inactive;
	@JsonProperty private String button2Hover;
}
