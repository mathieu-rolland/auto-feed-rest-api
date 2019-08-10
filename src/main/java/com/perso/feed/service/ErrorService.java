package com.perso.feed.service;

import org.springframework.stereotype.Service;

import com.perso.feed.model.ErrorCodeEnum;
import com.perso.feed.model.ErrorDescription;

@Service
public class ErrorService {

	public ErrorDescription generateReturnDescription( int code , String error ) {
		return new ErrorDescription( error , code );
	}
	
	public ErrorDescription generateReturnDescription( ErrorCodeEnum errorDesc ) {
		return new ErrorDescription( errorDesc.getMessage() , errorDesc.getErrorCode() );
	} 
	
}
