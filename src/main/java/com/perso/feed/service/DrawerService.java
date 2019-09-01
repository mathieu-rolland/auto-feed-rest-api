package com.perso.feed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perso.feed.config.BoxContext;
import com.perso.feed.model.Drawer;
import com.perso.feed.model.DrawerStateEnum;
import com.perso.feed.model.ErrorCodeEnum;
import com.perso.feed.model.ErrorDescription;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DrawerService {

	@Autowired
	private BoxContext boxContext;
	
	@Autowired
	private ErrorService errorService;
	
	public ErrorDescription openingDrawer( Drawer drawer ) {
		
		if( !isOpenOrOpenning(drawer) ) { 
		
			drawer.setState( DrawerStateEnum.OPENING );
			
			boxContext.getLedPin().high();
			drawer.getMotorPlus().high();
			drawer.getMotorLess().low();
			
			return null;
		}else {
			log.warn( "The drawer {} is already openned." , drawer.getName() );
			return errorService.generateReturnDescription( ErrorCodeEnum.ALREADY_OPEN );
		}
		
	}

	public ErrorDescription closingDrawer( Drawer drawer ) throws InterruptedException {
		
		if( drawer.isClosed() ) {
			log.warn( "The drawer {} is already closed." , drawer.getName() );
			return errorService.generateReturnDescription( ErrorCodeEnum.ALREADY_CLOSED );
		}else {
			drawer.setState( DrawerStateEnum.CLOSING );
			
			boxContext.getLedPin().high();
			drawer.getMotorLess().high();
			drawer.getMotorPlus().low();
			
			return null;
		}
		
	}
	
	public void stopInError( Drawer drawer ) {
		boxContext.getLedPin().low();
		drawer.getMotorPlus().low();
		log.error( "The drawer {} stpped in error." , drawer.getName() );
	}
	
	public boolean isOpenOrOpenning( Drawer drawer ) {
		return drawer.isOpen();
				
	}
	
}
