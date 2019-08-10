package com.perso.feed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perso.feed.model.dto.BoxResponseDTO;
import com.perso.feed.service.BoxService;

@RestController
@RequestMapping("/box")
public class BoxRestController {

	@Autowired
	private BoxService boxService;
	
	@RequestMapping("/state")
	public ResponseEntity<BoxResponseDTO> getCurrentState(){
		return new ResponseEntity<BoxResponseDTO>( boxService.generateState( null ) , HttpStatus.OK );
	}
	
}
