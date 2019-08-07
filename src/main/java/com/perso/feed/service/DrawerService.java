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
	
	public Drawer openDrawer( Drawer drawer ) throws InterruptedException {
		
		drawer.setState( DrawerStateEnum.OPENING );
		
		boxContext.getLedPin().high();
		drawer.getMotorPlus().high();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			stopInError(drawer);
			throw e;
		}
		
		boxContext.getLedPin().low();
		drawer.getMotorPlus().low();
		
		drawer.setState( DrawerStateEnum.OPEN );
		
		return drawer;
		
	}

	public Drawer closeDrawer( Drawer drawer ) throws InterruptedException {
		
		drawer.setState( DrawerStateEnum.CLOSING );
		
		boxContext.getLedPin().high();
		drawer.getMotorLess().high();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			stopInError(drawer);
			throw e;
		}
		
		boxContext.getLedPin().low();
		drawer.getMotorLess().low();
		
		drawer.setState( DrawerStateEnum.CLOSED );
		
		return drawer;
		
	}
	
	private void stopInError( Drawer drawer ) {
		boxContext.getLedPin().low();
		drawer.getMotorPlus().low();
	}
	
}
