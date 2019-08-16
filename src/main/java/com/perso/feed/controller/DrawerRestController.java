package com.perso.feed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perso.feed.model.ErrorDescription;
import com.perso.feed.model.dto.BoxResponseDTO;
import com.perso.feed.service.BoxService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/drawer")
@Slf4j
public class DrawerRestController {

	@Autowired
	private BoxService boxService;
	
	@RequestMapping("/{number}/open")
	public ResponseEntity<BoxResponseDTO> openDoor( @PathVariable("number") int number ) throws InterruptedException {
		log.info( "Open the door" );
		ErrorDescription error = boxService.openDrawer(number);
		return new ResponseEntity<BoxResponseDTO>( boxService.generateState( error ) , HttpStatus.OK );
	}

	@RequestMapping("/{number}/close")
	public ResponseEntity<BoxResponseDTO> closeDoor(@PathVariable("number") int number) throws InterruptedException {
		log.info( "Close the door" );
		ErrorDescription error = boxService.closeDrawer(number);
		return new ResponseEntity<BoxResponseDTO>( boxService.generateState( error ) , HttpStatus.OK );
	}
	
}
