package com.perso.feed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perso.feed.model.ErrorDescription;
import com.perso.feed.model.dto.BoxResponseDTO;
import com.perso.feed.service.BoxService;
import com.perso.feed.service.CameraService;

@RestController
@RequestMapping("/camera")
public class CameraRestController {

	@Autowired
	private CameraService cameraService;
	
	@Autowired
	private BoxService boxService;
	
	@RequestMapping("/start")
	public ResponseEntity<BoxResponseDTO> startCamera(){
		ErrorDescription error = cameraService.startStreaming();
		return new ResponseEntity<BoxResponseDTO>( boxService.generateState( error ) , HttpStatus.OK );
	}
	
	@RequestMapping("/stop")
	public ResponseEntity<BoxResponseDTO> stopCamera(){
		ErrorDescription error = cameraService.stopStreaming();
		return new ResponseEntity<BoxResponseDTO>( boxService.generateState(error) , HttpStatus.OK );
	}
	
	@RequestMapping("/force/stop")
	public ResponseEntity<BoxResponseDTO> forceKillCamera(){
		ErrorDescription error = cameraService.forceToKill();
		return new ResponseEntity<BoxResponseDTO>( boxService.generateState(error) , HttpStatus.OK );
	}
	
}
