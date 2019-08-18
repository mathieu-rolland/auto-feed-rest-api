package com.perso.feed.material.listener;

import java.util.ArrayList;
import java.util.List;

import com.perso.feed.event.BoxEvent;
import com.perso.feed.event.BoxObservable;
import com.perso.feed.event.BoxObserver;
import com.perso.feed.model.Drawer;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Getter
@RequiredArgsConstructor
@Slf4j
public class GpioPinListenerDigitalEventListener implements GpioPinListenerDigital, BoxObservable {

	@NonNull
	private Drawer drawer;
	@NonNull
	private BoxEvent event;
	
	private List<BoxObserver> observers = new ArrayList<>();
	
	@Override
	public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
		
		log.info("Event received for drawer {}" , drawer.getName() + " for event listener {} " , event );
		if( event.getState().isLow() ) {
			log.info("Event tak into account for drawer {} with state {}", drawer.getName() , drawer.getState() );
			sendEvent();
			log.info("Drawer done {} with state {}", drawer.getName() , drawer.getState() );
		}else {
			log.info("Event for {} is ignore" , event);
		}
		
	}

	@Override
	public void sendEvent() {
		for( BoxObserver obs : observers ) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					obs.receivedEvent( event , drawer);
				}
				
			}).start();
		}
	}

	@Override
	public void addObserver(BoxObserver box) {
		this.observers.add( box );
	}

	@Override
	public void removeObserver(BoxObserver box) {
		observers.remove(box);
	}

}
