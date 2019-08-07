package com.perso.feed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perso.feed.model.Drawer;
import com.perso.feed.service.BoxService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/drawer")
@Slf4j
public class DoorRestController {

	@Autowired
	private BoxService boxService;
	
	@RequestMapping("/{number}/open")
	public Drawer openDoor( @PathVariable("number") int number ) throws InterruptedException {
		log.info( "Open the door" );
		Drawer d = boxService.openDrawer(number);
		return d;
	}

	@RequestMapping("/{number}/close")
	public Drawer closeDoor(@PathVariable("number") int number) throws InterruptedException {
		log.info( "Close the door" );
		return boxService.closeDrawer(number);
	}
	
}
