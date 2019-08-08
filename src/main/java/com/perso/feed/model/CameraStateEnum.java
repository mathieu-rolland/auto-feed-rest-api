package com.perso.feed.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CameraStateEnum {

	START("started") , STOP("stoped");
	
	private String name;
	
}
