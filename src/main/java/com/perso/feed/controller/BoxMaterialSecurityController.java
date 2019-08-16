package com.perso.feed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perso.feed.model.ErrorDescription;
import com.perso.feed.model.dto.BoxResponseDTO;
import com.perso.feed.service.BoxService;

@RestController
@RequestMapping("/material/security")
public class BoxMaterialSecurityController {

	@Autowired
	private BoxService boxService;
	
	@RequestMapping("/stop/force")
	public ResponseEntity<BoxResponseDTO> forceToStop() 
	{
		ErrorDescription errorDesc =  boxService.stopAll();
		return new ResponseEntity<BoxResponseDTO>( boxService.generateState( errorDesc ) , HttpStatus.OK );
	}
	
}
