package com.perso.feed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Drawer {

	private int number;
	private String name;
	private DrawerStateEnum state;
	
	@JsonIgnore
	private transient GpioPinDigitalOutput motorPlus;
	@JsonIgnore
	private transient GpioPinDigitalOutput motorLess;
	@JsonIgnore
	private transient GpioPinDigitalInput openedSensor;
	@JsonIgnore
	private transient GpioPinDigitalInput closedSensor;
	
	public boolean isOpen() {
		log.info( "{} : {}" , openedSensor.getName() , openedSensor.getState() );
		log.info( "{} : {}" , closedSensor.getName() , closedSensor.getState() );
		return openedSensor.isLow();
	}
	
	public boolean isClosed() {
		log.info( "{} : {}" , openedSensor.getName() , openedSensor.getState() );
		log.info( "{} : {}" , closedSensor.getName() , closedSensor.getState() );
		return closedSensor.isLow();
	}
	
	public String toString() {
		return "Number : " + number + "Name : " + name + ", state : " + state.getName();
	}
	
}
