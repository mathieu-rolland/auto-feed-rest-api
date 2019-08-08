package com.perso.feed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perso.feed.config.BoxContext;
import com.perso.feed.converter.dto.impl.BoxStateDTOConverter;
import com.perso.feed.model.BoxState;
import com.perso.feed.model.Drawer;
import com.perso.feed.model.DrawerStateEnum;
import com.perso.feed.model.dto.BoxStateDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoxService {

	@Autowired
	private BoxContext boxContext;
	
	@Autowired
	private DrawerService drawerService;
	
	@Autowired
	private BoxStateDTOConverter boxStateDtoConverter;
	
	@Autowired
	private CameraService cameraService;
	
	public Drawer openDrawer( int number ) throws InterruptedException {
		if( number == 1 ) {
			return drawerService.openingDrawer( boxContext.getDrawer1() );
		}else {
			log.warn("The drawer number 2 is not implemented yet.");
			return null;
		}
		
	}
	
	public Drawer closeDrawer( int number ) throws InterruptedException {
		if( number == 1 ) {
			return drawerService.closingDrawer( boxContext.getDrawer1() );
		}else {
			log.warn("The drawer number 2 is not implemented yet.");
			return null;
		}
			
	}

	public void stopAll() {

		boxContext.getDrawer1().getMotorLess().low();
		boxContext.getDrawer1().getMotorPlus().low();
		boxContext.getDrawer2().getMotorLess().low();
		boxContext.getDrawer2().getMotorPlus().low();

		boxContext.getLedPin().low();
		
		cameraService.forceToKill();
		
		setDrawStateAfterForceStop( boxContext.getDrawer1() );
		setDrawStateAfterForceStop( boxContext.getDrawer2() );
		
	}
	
	private void setDrawStateAfterForceStop( Drawer drawer ) {
		switch( drawer.getState() ) {
			case CLOSING:
				drawer.setState( DrawerStateEnum.CLOSED );
				break;
			case OPENING:
				drawer.setState( DrawerStateEnum.OPEN );
				break;
			default:
				log.info("L'état du moteur n'a pas été forcé.");
				return;
		}
		log.info("L'état du moteur {}  est forcé à {} ", drawer.getName() , drawer.getState() );
	}
	
	public BoxStateDTO generateState() {
		BoxState state = new BoxState();
		state.setCamera( boxContext.getCamera() );
		state.setDrawer1( boxContext.getDrawer1() );
		state.setDrawer2( boxContext.getDrawer2() );
		state.setLedState( boxContext.getLedPin() );
		return boxStateDtoConverter.generateDTO( state );
	}
	
}
