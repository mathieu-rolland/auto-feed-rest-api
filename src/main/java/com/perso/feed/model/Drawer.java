package com.perso.feed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
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
		return openedSensor.isLow();
	}
	
	public boolean isClosed() {
		return closedSensor.isLow();
	}
	
	public String toString() {
		return "Number : " + number + "Name : " + name + ", state : " + state.getName();
	}

	public void stop() {
		motorLess.low();
		motorPlus.low();
		log.info( "Stop motor of {}" , name );
	}
	
	protected ReturnCodeEnum open() {
		
		if( !isOpen() ) {
			state = DrawerStateEnum.OPENING;
			
			motorPlus.high();
			motorLess.low();
			return ReturnCodeEnum.DRAWER_OPENNING;
		}
		return ReturnCodeEnum.ALREADY_OPEN;
		
	}
	
	protected ReturnCodeEnum close() {
		
		if( !isClosed() ) {
			state = DrawerStateEnum.CLOSING;
			
			motorPlus.low();
			motorLess.high();
			return ReturnCodeEnum.DRAWER_CLOSING;
		}
		return ReturnCodeEnum.ALREADY_CLOSED;
		
	}
	
}
