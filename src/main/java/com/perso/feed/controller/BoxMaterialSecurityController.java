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
@RequestMapping("/material/security")
public class BoxMaterialSecurityController {

	@Autowired
	private Box box;
	
	@Autowired
	private ResponseService responseService;
	
	@RequestMapping("/stop/force")
	public ResponseEntity<BoxResponseDTO> forceToStop() 
	{
		ReturnCodeEnum errorDesc = box.stopAll();
		return responseService.generateResponse( errorDesc );
	}
	
}
