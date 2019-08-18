package com.perso.feed.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.perso.feed.event.BoxEvent;
import com.perso.feed.event.BoxObservable;
import com.perso.feed.event.BoxObserver;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@RequiredArgsConstructor
public class Camera implements BoxObservable{

	private CameraStateEnum state;
	
	private Process process;
	
	private List<BoxObserver> observers = new ArrayList<BoxObserver>();
	
	@NonNull
	private String cmd;
	
	@NonNull
	private String forceCameraStop;
	
	public boolean cameraIsRunning() {
		return CameraStateEnum.RUNNING == state;
	}
	
	public ReturnCodeEnum startStreaming() {
		if( !cameraIsRunning() ){
			log.info("Start camera");
			try {			
				log.info("Running the commande : {} " , cmd);
				process = Runtime.getRuntime().exec( cmd );
			} catch (IOException e) {
				e.printStackTrace();
			}
			state = CameraStateEnum.RUNNING;
			return ReturnCodeEnum.CAMERA_STARTED;
		}else {
			return ReturnCodeEnum.CAMERA_ALREADY_STARTED;
		}
	}

	public ReturnCodeEnum stopStreaming() {
		if( cameraIsRunning() ) {
			if( process != null ) {
				process.destroy();
			}
			return forceToKill();
		}else {
			log.info("The camera is not running");
			return ReturnCodeEnum.CAMERA_ALREADY_CLOSED;
		}
	}
	
	public ReturnCodeEnum forceToKill() {
		log.info("Force to kill the camera streaming");
		try {
			Runtime.getRuntime().exec( forceCameraStop );
		} catch (IOException e) {
			e.printStackTrace();
			return ReturnCodeEnum.FAILED_TO_CLOSE;
		}
		state = CameraStateEnum.STOPPED;
		log.info("The camera streaming is now stopped");
		return ReturnCodeEnum.CAMERA_ALREADY_CLOSED;
	}

	@Override
	public void sendEvent() {
		this.observers.forEach( e -> {
			e.receivedEvent( BoxEvent.STREAM_START , null);
		});
	}

	@Override
	public void addObserver(BoxObserver box) {
		this.observers.add(box);
	}

	@Override
	public void removeObserver(BoxObserver box) {
		this.observers.remove(box);
	}
	
}
