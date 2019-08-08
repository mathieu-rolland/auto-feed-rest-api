package com.perso.feed.model.dto;

import com.perso.feed.model.DrawerStateEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DrawerDTO {

	private DrawerStateEnum state;
	private String name;
	
}
