package com.perso.feed.model;

import com.pi4j.io.gpio.GpioPinDigitalOutput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoxState {

	private Drawer drawer1;
	private Drawer drawer2;
	
	private GpioPinDigitalOutput ledState;
	
	private Camera camera;
	
	private Flash flash;
	
}
