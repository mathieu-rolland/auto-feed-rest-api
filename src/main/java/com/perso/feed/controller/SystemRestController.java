package com.perso.feed.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perso.feed.service.SystemService;

@RestController
@RequestMapping("/system")
public class SystemRestController {

	@Autowired
	private SystemService systemService;
	
	
	@RequestMapping("/cpu/temperature")
	public ResponseEntity<Float> getTemperature(){
		
		try {
			return new ResponseEntity<Float>(systemService.getCpuTemperature(), HttpStatus.OK) ;
		} catch (NumberFormatException | UnsupportedOperationException | IOException | InterruptedException e) {
			e.printStackTrace();
			return new ResponseEntity<Float>(Float.MAX_VALUE, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
