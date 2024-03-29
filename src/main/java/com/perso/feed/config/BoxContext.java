package com.perso.feed.config;

import com.perso.feed.material.emulator.IPinProvider;
import com.perso.feed.material.listener.GpioPinListenerDigitalEventListener;
import com.perso.feed.model.Camera;
import com.perso.feed.model.CameraStateEnum;
import com.perso.feed.model.Drawer;
import com.perso.feed.model.DrawerStateEnum;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

import lombok.Getter;

@Getter
public class BoxContext {

	private Drawer drawer1;
	private Drawer drawer2;
	
	private GpioPinDigitalOutput ledPin;
	
	private GpioPinListenerDigitalEventListener closingListener;
	private GpioPinListenerDigitalEventListener openingListener;
	
	private Camera camera;
	
	public void setupGpio( GpioController gpio , IPinProvider pinProvider ) {
		
		
		ledPin = gpio.provisionDigitalOutputPin( pinProvider.getPin00() , "MyLED", PinState.HIGH);
		ledPin.setShutdownOptions(true, PinState.LOW);
		
		GpioPinDigitalOutput motor1Pin1 = gpio.provisionDigitalOutputPin( pinProvider.getPin25() , "motor1Pin1", PinState.LOW);
		motor1Pin1.setShutdownOptions(true, PinState.LOW);
		
		GpioPinDigitalOutput motor1Pin2 = gpio.provisionDigitalOutputPin(pinProvider.getPin24(), "motor1Pin2", PinState.LOW);
		motor1Pin2.setShutdownOptions(true, PinState.LOW);
		
		drawer1 = new Drawer( "Drawer1" , DrawerStateEnum.CLOSED, motor1Pin1, motor1Pin2);
		
		//TODO : implementer la gestion du 2eme moteur.
		drawer2 = new Drawer( "Drawer2" , DrawerStateEnum.CLOSED , null , null );
		
		GpioPinDigitalInput course1MoteurClosed = gpio.provisionDigitalInputPin( pinProvider.getPin01() , "CfC1MoteurClosed"  );
		GpioPinDigitalInput course1MoteurOpened = gpio.provisionDigitalInputPin( pinProvider.getPin02() , "CfC1MoteurOpened"  );
		
		closingListener = new GpioPinListenerDigitalEventListener( drawer1 , DrawerStateEnum.CLOSING , DrawerStateEnum.CLOSED );
		openingListener = new GpioPinListenerDigitalEventListener( drawer1 , DrawerStateEnum.OPENING , DrawerStateEnum.OPEN );
		
		course1MoteurClosed.addListener( closingListener );
		course1MoteurOpened.addListener( openingListener );
		
		camera = new Camera( CameraStateEnum.STOPPED , null );
		
	}
	
}
