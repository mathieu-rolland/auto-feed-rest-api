package com.perso.feed.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.perso.feed.config.BoxContext;
import com.perso.feed.model.CameraStateEnum;
import com.perso.feed.model.ErrorCodeEnum;
import com.perso.feed.model.ErrorDescription;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CameraService {

	//private static final int CAMERA_STREAM_PORT = 8090;
	
	/*
	private static final String CMD_START_STREAM = "/product/auto-feed/mjpg-streamer-experimental/mjpg_streamer "
													+ "-o \"output_http.so -w /product/auto-feed/mjpg-streamer-experimental/www -p "+CAMERA_STREAM_PORT+"\" "
															+ "-i \"input_raspicam.so\" &";
	*/
	
	
	@Value("${auto-feed.camera.cmd}")
	private String cmd;
	
	@Value("${auto-feed.camera.cmd_force}")
	private String forceCameraStop;
	
	@Autowired
	private BoxContext boxContext;
	
	@Autowired
	private ErrorService errorService;
	
	public boolean cameraIsRunning() {
		return CameraStateEnum.RUNNING == boxContext.getCamera().getState();
	}
	
	public ErrorDescription startStreaming() {
		if( !cameraIsRunning() ){
			log.info("Start camera");
			try {			
				log.info("Running the commande : {} " , cmd);
				Process p = Runtime.getRuntime().exec( cmd );
				boxContext.getCamera().setProcess( p );			
			} catch (IOException e) {
				e.printStackTrace();
			}
			boxContext.getCamera().setState( CameraStateEnum.RUNNING );
			return null;
		}else {
			return errorService.generateReturnDescription( ErrorCodeEnum.CAMERA_ALREADY_STARTED );
		}
	}

	public ErrorDescription stopStreaming() {
		Process cameraProcess = boxContext.getCamera().getProcess();
		if( cameraIsRunning() ) {
			if( cameraProcess != null ) {
				cameraProcess.destroy();
			}
			return forceToKill();
		}else {
			log.info("The camera is not running");
			return errorService.generateReturnDescription( ErrorCodeEnum.CAMERA_ALREADY_CLOSED );
		}
	}
	
	public ErrorDescription forceToKill() {
		log.info("Force to kill the camera streaming");
		try {
			Runtime.getRuntime().exec( forceCameraStop );
		} catch (IOException e) {
			e.printStackTrace();
			return errorService.generateReturnDescription( ErrorCodeEnum.FAILED_TO_CLOSE );
		}
		boxContext.getCamera().setState( CameraStateEnum.STOPPED );
		log.info("The camera streaming is now stopped");
		return errorService.generateReturnDescription( ErrorCodeEnum.CAMERA_ALREADY_CLOSED );
	}
	
}
