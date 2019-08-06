package com.perso.feed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perso.feed.model.Drawer;
import com.perso.feed.service.BoxService;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/door")
@Slf4j
public class DoorRestController {

	@Autowired
	private BoxService boxService;
	
	
	@RequestMapping("/{number}/open")
	public Drawer openDoor( @PathVariable("number") int number ) throws InterruptedException {
		log.info( "Open the door" );
		return boxService.openDrawer(number);
	}

	@RequestMapping("/{number}/close")
	public Drawer closeDoor(@PathVariable("number") int number) throws InterruptedException {
		log.info( "Close the door" );
		return boxService.closeDrawer(number);
	}
	
	@RequestMapping("/test")
	public String testDoor() {
		log.info( "Start the PI test..." );
		
		final GpioController gpio = GpioFactory.getInstance();
		
		
		GpioPinDigitalOutput motor1Pin1 = (GpioPinDigitalOutput) gpio.getProvisionedPin("motor1Pin1");
		GpioPinDigitalOutput motor1Pin2 = (GpioPinDigitalOutput) gpio.getProvisionedPin("motor1Pin2");
		
        log.info("End test successfully");
        
		return "Test Done!!";
	}
	
	
}
