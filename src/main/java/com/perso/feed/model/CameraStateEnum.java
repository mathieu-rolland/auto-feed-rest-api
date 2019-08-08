package com.perso.feed.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CameraStateEnum {

	RUNNING("running") , STOPPED("stopped");
	
	private String name;
	
}
