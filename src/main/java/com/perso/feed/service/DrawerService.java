package com.perso.feed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perso.feed.config.BoxContext;
import com.perso.feed.model.Drawer;
import com.perso.feed.model.DrawerStateEnum;

@Service
public class DrawerService {

	@Autowired
	private BoxContext boxContext;
	
	public Drawer openingDrawer( Drawer drawer ) throws InterruptedException {
		
		drawer.setState( DrawerStateEnum.OPENING );
		
		boxContext.getLedPin().high();
		drawer.getMotorPlus().high();
		
		return drawer;
		
	}

	public Drawer closingDrawer( Drawer drawer ) throws InterruptedException {
		
		drawer.setState( DrawerStateEnum.CLOSING );
		
		boxContext.getLedPin().high();
		drawer.getMotorLess().high();
		
		return drawer;
		
	}
	
	public void stopInError( Drawer drawer ) {
		boxContext.getLedPin().low();
		drawer.getMotorPlus().low();
	}
	
}
