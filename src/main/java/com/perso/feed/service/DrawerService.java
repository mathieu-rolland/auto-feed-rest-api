package com.perso.feed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perso.feed.config.BoxContext;
import com.perso.feed.model.Drawer;
import com.perso.feed.model.DrawerStateEnum;
import com.perso.feed.model.ErrorCodeEnum;
import com.perso.feed.model.ErrorDescription;

@Service
public class DrawerService {

	@Autowired
	private BoxContext boxContext;
	
	@Autowired
	private ErrorService errorService;
	
	public ErrorDescription openingDrawer( Drawer drawer ) throws InterruptedException {
		
		if( !isOpenOrOpenning(drawer) ) { 
		
			drawer.setState( DrawerStateEnum.OPENING );
			
			boxContext.getLedPin().high();
			drawer.getMotorPlus().high();
			drawer.getMotorLess().low();
			
			return null;
		}else {
			return errorService.generateReturnDescription( ErrorCodeEnum.ALREADY_OPEN );
		}
		
	}

	public ErrorDescription closingDrawer( Drawer drawer ) throws InterruptedException {
		
		if( isOpenOrOpenning( drawer ) ) {
		
			drawer.setState( DrawerStateEnum.CLOSING );
			
			boxContext.getLedPin().high();
			drawer.getMotorLess().high();
			drawer.getMotorPlus().low();
			
			return null;
		}else {
			return errorService.generateReturnDescription( ErrorCodeEnum.ALREADY_CLOSED );
		}
		
	}
	
	public void stopInError( Drawer drawer ) {
		boxContext.getLedPin().low();
		drawer.getMotorPlus().low();
	}
	
	public boolean isOpenOrOpenning( Drawer drawer ) {
		return DrawerStateEnum.OPEN.equals( drawer.getState() ) 
				|| DrawerStateEnum.OPENING.equals( drawer.getState() );
				
	}
	
}
