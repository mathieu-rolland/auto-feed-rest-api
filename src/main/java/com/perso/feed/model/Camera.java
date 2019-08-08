package com.perso.feed.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Camera {

	private CameraStateEnum state;
	
	private Process process;
	
}
