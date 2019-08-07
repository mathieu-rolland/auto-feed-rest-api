package com.perso.feed.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CameraStateEnum {

	START("start") , STOP("stop");
	
	private String name;
	
}
