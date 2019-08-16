package com.perso.feed.controller;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perso.feed.model.dto.BoxResponseDTO;
import com.perso.feed.service.BoxService;
import com.perso.feed.service.SoundPlayerBean;

@RestController
@RequestMapping("/box")
public class BoxRestController {

	@Autowired
	private BoxService boxService;
	
	@Autowired
	private SoundPlayerBean soundPlayerService;
	
	@RequestMapping("/state")
	public ResponseEntity<BoxResponseDTO> getCurrentState(){
		return new ResponseEntity<BoxResponseDTO>( boxService.generateState( null ) , HttpStatus.OK );
	}
	
	@RequestMapping("/sound/play")
	public void playSound() throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {
		soundPlayerService.playSound();
	}
	
	
}
