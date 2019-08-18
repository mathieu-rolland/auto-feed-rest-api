package com.perso.feed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.perso.feed.converter.dto.impl.BoxResponseDTOConverter;
import com.perso.feed.model.Box;
import com.perso.feed.model.BoxResponse;
import com.perso.feed.model.BoxState;
import com.perso.feed.model.ReturnCodeEnum;
import com.perso.feed.model.dto.BoxResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ResponseService {

	@Autowired
	private BoxResponseDTOConverter boxResponseDTOConverter;
	
	@Autowired
	private Box box;
	
	public ResponseEntity<BoxResponseDTO> generateResponse( ReturnCodeEnum returnCode ){
		if( returnCode.getErrorCode() != 0 ) {
			log.error( returnCode.getMessage() );
		}else {
			log.info( returnCode.getMessage() );
		}
		return new ResponseEntity<BoxResponseDTO>( generateState( returnCode ) , HttpStatus.OK );
	}
	
	public BoxResponseDTO generateState( ReturnCodeEnum errorDesc ) {
		
		BoxState state = new BoxState();
		state.setCamera( box.getCamera() );
		state.setDrawer1( box.getDrawers().get(1) );
		state.setDrawer2( box.getDrawers().get(2) );
//		state.setLedState( box.getLedPin() );
			
		return boxResponseDTOConverter.generateDTO( new BoxResponse(state, errorDesc) );
	}
	
}
