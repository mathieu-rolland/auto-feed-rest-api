package com.perso.feed.model;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class SecurityDrawer{

	private int timeBeforeAlert;
	private Drawer drawer;
	
	public ReturnCodeEnum open() {
		startSecurity();
		return drawer.open();
	}
	
	public ReturnCodeEnum close() {
		startSecurity();
		return drawer.close();
	}
	
	private void startSecurity() {
		
		new Thread( new Runnable() {
			
			@Override
			public void run() {
				log.info( "Start security for {} " , drawer.getName() );
	
				try {
					Thread.sleep( timeBeforeAlert * 1000 );
				} catch (InterruptedException e) {
					e.printStackTrace();
					log.error( "Thread sleep interrupted..." );
				}
				
				if( drawer.getMotorLess().isHigh() || drawer.getMotorPlus().isHigh() ) {
					//Send mail alert
					log.error( "The drawer should be open and is still always running!!!!" );
					drawer.stop();
				}else {
					log.info("The security for {} stay silent." , drawer.getName() );
				}
			}
		} ).start();
		
	}
	
}
