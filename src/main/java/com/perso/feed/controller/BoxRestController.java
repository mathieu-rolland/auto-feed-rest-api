package com.perso.feed.controller;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perso.feed.model.Box;
import com.perso.feed.model.ReturnCodeEnum;
import com.perso.feed.model.dto.BoxResponseDTO;
import com.perso.feed.service.ResponseService;

@RestController
@RequestMapping("/box")
public class BoxRestController {

	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private Box box;
	
	@RequestMapping("/state")
	public ResponseEntity<BoxResponseDTO> getCurrentState(){
		return responseService.generateResponse( ReturnCodeEnum.NO_ERROR );
	}
	
	@RequestMapping("/sound/play")
	public void playSound() throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {
		box.playSound();
	}
	
}
