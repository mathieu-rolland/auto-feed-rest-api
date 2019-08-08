package com.perso.feed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perso.feed.model.dto.BoxStateDTO;
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
	public ResponseEntity<BoxStateDTO> startCamera(){
		cameraService.startStreaming();
		return new ResponseEntity<BoxStateDTO>( boxService.generateState() , HttpStatus.OK );
	}
	
	@RequestMapping("/stop")
	public ResponseEntity<BoxStateDTO> stopCamera(){
		cameraService.stopStreaming();
		return new ResponseEntity<BoxStateDTO>( boxService.generateState() , HttpStatus.OK );
	}
	
}
