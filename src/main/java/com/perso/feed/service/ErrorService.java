package com.perso.feed.service;

import org.springframework.stereotype.Service;

import com.perso.feed.model.ReturnCodeEnum;
import com.perso.feed.model.ErrorDescription;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ErrorService {

	public ErrorDescription generateReturnDescription( int code , String error ) {
		return new ErrorDescription( error , code );
	}
	
	public ErrorDescription generateReturnDescription( ReturnCodeEnum errorDesc ) {
		if( errorDesc.getErrorCode() != 0 ) {
			log.error( errorDesc.getMessage() );
		}
		return new ErrorDescription( errorDesc.getMessage() , errorDesc.getErrorCode() );
	}

	public void sendMail(Exception e) {
		log.info("Send mail on error occured." , e);
	} 
	
}
