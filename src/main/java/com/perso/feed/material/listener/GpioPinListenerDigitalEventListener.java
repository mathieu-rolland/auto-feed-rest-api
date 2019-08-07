package com.perso.feed.material.listener;

import com.perso.feed.model.Drawer;
import com.perso.feed.model.DrawerStateEnum;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@Getter
@AllArgsConstructor
@Slf4j
public class GpioPinListenerDigitalEventListener implements GpioPinListenerDigital {

	private Drawer drawer;
	private DrawerStateEnum eventToListen;
	private DrawerStateEnum finalState;
	
	@Override
	public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
		
		log.info("Event received for drawer {}" , drawer.getName());
		if( drawer.getState().equals( eventToListen ) ) {
			log.info("Event tak into account for drawer {} with state {}", drawer.getName() , drawer.getState() );
			drawer.getMotorLess().low();
			drawer.getMotorPlus().low();
			drawer.setState( finalState );
			log.info("Drawer done {} with state {}", drawer.getName() , drawer.getState() );
		}
		
	}

}
