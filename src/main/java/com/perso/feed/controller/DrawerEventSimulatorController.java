package com.perso.feed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perso.feed.config.SpringProfile;
import com.perso.feed.model.Box;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/emulator")
@Profile( value = { SpringProfile.EMULATOR } )
@Slf4j
public class DrawerEventSimulatorController {

	@Autowired
	private Box box;
	
	@RequestMapping("/{number}/closedEvent")
	public void drawerEventClosed(@PathVariable("number") int number) {
		log.info("ClosedEvent called for {}" , number);
		box.debug();
		box.getClosingListener().handleGpioPinDigitalStateChangeEvent( 
				new GpioPinDigitalStateChangeEvent( box.getDrawers().get(number).getClosedSensor() , box.getDrawers().get(number).getOpenedSensor(), PinState.LOW) 
		);
	}
	
	@RequestMapping("/{number}/openedEvent")
	public void drawerEventOpened(@PathVariable("number") int number) {
		log.info("OpenedEvent called for {}" , number);
		box.getOpeningListener().handleGpioPinDigitalStateChangeEvent( 
				new GpioPinDigitalStateChangeEvent( box.getDrawers().get(number).getOpenedSensor() , box.getDrawers().get(number).getOpenedSensor(), PinState.HIGH) 
		);
	}
	
}
