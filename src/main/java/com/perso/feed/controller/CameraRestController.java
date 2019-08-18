package com.perso.feed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perso.feed.model.Box;
import com.perso.feed.model.ReturnCodeEnum;
import com.perso.feed.model.dto.BoxResponseDTO;
import com.perso.feed.service.ResponseService;

@RestController
@RequestMapping("/camera")
public class CameraRestController {

	@Autowired
	private Box box;
	
	@Autowired
	private ResponseService responseService;
	
	@RequestMapping("/start")
	public ResponseEntity<BoxResponseDTO> startCamera(){
		ReturnCodeEnum error = box.getCamera().startStreaming();
		return responseService.generateResponse( error );
	}
	
	@RequestMapping("/stop")
	public ResponseEntity<BoxResponseDTO> stopCamera(){
		ReturnCodeEnum error = box.getCamera().stopStreaming();
		return responseService.generateResponse( error );
	}
	
	@RequestMapping("/force/stop")
	public ResponseEntity<BoxResponseDTO> forceKillCamera(){
		ReturnCodeEnum error = box.getCamera().forceToKill();
		return responseService.generateResponse( error );
	}
	
}
