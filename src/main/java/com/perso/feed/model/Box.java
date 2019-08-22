package com.perso.feed.model;

import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import com.perso.feed.event.BoxEvent;
import com.perso.feed.event.BoxObserver;
import com.perso.feed.material.emulator.IPinProvider;
import com.perso.feed.material.listener.GpioPinListenerDigitalEventListener;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Box implements BoxObserver{

	private HashMap<Integer, SecurityDrawer> drawers = new HashMap<Integer, SecurityDrawer>();
	
	private Camera camera;
	
	private ThreadPoolTaskScheduler scheduler;
	
	private SoundPlayer player;
	
	GpioPinListenerDigitalEventListener closingListener;
	GpioPinListenerDigitalEventListener openingListener;
	
	public void setupBox( GpioController gpio , IPinProvider pinProvider , int waitBeforeAlerte) {
		//ledPin = gpio.provisionDigitalOutputPin( pinProvider.getPin00() , "MyLED", PinState.HIGH);
		//ledPin.setShutdownOptions(true, PinState.LOW);
		
		GpioPinDigitalOutput motor1Pin1 = gpio.provisionDigitalOutputPin( pinProvider.getPin25() , "motor1Pin1", PinState.LOW);
		motor1Pin1.setShutdownOptions(true, PinState.LOW);
		
		GpioPinDigitalOutput motor1Pin2 = gpio.provisionDigitalOutputPin(pinProvider.getPin24(), "motor1Pin2", PinState.LOW);
		motor1Pin2.setShutdownOptions(true, PinState.LOW);
		
		GpioPinDigitalInput course1MoteurOpened = gpio.provisionDigitalInputPin( pinProvider.getPin01() , "CfC1MoteurClosed"  );
		GpioPinDigitalInput course1MoteurClosed = gpio.provisionDigitalInputPin( pinProvider.getPin02() , "CfC1MoteurOpened"  );
		
		SecurityDrawer drawer = new SecurityDrawer( 1 , "Drawer1" , DrawerStateEnum.CLOSED, motor1Pin1, motor1Pin2 , course1MoteurOpened , course1MoteurClosed ,waitBeforeAlerte);
		drawers.put( drawer.getNumber() , drawer );
		
		closingListener = new GpioPinListenerDigitalEventListener( drawer , BoxEvent.DRAWER_CLOSE );
		openingListener = new GpioPinListenerDigitalEventListener( drawer , BoxEvent.DRAWER_OPEN );
		
		course1MoteurClosed.addListener( closingListener );
		course1MoteurOpened.addListener( openingListener );
	
		//TODO : implementer la gestion du 2eme moteur.
		drawer = new SecurityDrawer( 2 , "Drawer2" , DrawerStateEnum.CLOSED , null , null , null , null,waitBeforeAlerte);
		drawers.put( drawer.getNumber() , drawer );
		
	}
	
	public void setupSound( String soundPath ) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		player = new SoundPlayer();
		player.setup( soundPath );
	}
	
	public void setupCamera( String cmd , String forceCmd ) {
		camera = new Camera(cmd, forceCmd);
	}
	
	public void playSound() throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {
		player.playSound();
	}
	
	public ReturnCodeEnum stopAll() {

		drawers.keySet().forEach( k -> {
			drawers.get(k).stop();
		});

//		boxContext.getLedPin().low();
		
		camera.forceToKill();
		
		return ReturnCodeEnum.NO_ERROR;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public void debug() {
		drawers.entrySet().forEach( k -> {
			log.info( "Key : {} / drawer : {}" , k , drawers.get(k));
		});
	}
	
	public void addTask( BoxTask task , Trigger trigger) {
		this.scheduler.schedule(task, trigger);
	}

	@Override
	public void receivedEvent(BoxEvent event, Drawer drawer) {
		drawer.stopAll();	
	}

}
