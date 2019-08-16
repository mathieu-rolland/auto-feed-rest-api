package com.perso.feed.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoxStateDTO {

	private DrawerDTO drawer1;
	private DrawerDTO drawer2;
	
	private GpioPinDigitalOutputDTO led;
	
	private CameraDTO camera;
	
}
