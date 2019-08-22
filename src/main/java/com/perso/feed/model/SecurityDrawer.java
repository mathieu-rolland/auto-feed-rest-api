package com.perso.feed.model;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityDrawer extends Drawer{

	private int timeBeforeAlert;
	
	public SecurityDrawer(int number, String name, DrawerStateEnum state, GpioPinDigitalOutput motorPlus,
			GpioPinDigitalOutput motorLess, GpioPinDigitalInput openedSensor, GpioPinDigitalInput closedSensor, int timeBeforeAlert) {
		
		super(number, name, state, motorPlus, motorLess, openedSensor, closedSensor);
		this.timeBeforeAlert = timeBeforeAlert;
		
	}
	
	public ReturnCodeEnum open() {
		startSecurity();
		return super.openDrawer();
	}
	
	public ReturnCodeEnum close() {
		startSecurity();
		return super.closeDrawer();
	}
	
	private void startSecurity() {
		
		new Thread( new Runnable() {
			
			@Override
			public void run() {
				log.info( "Start security for {} " , getName() );
	
				try {
					Thread.sleep( timeBeforeAlert * 1000 );
				} catch (InterruptedException e) {
					e.printStackTrace();
					log.error( "Thread sleep interrupted..." );
				}
				
				if( getMotorLess().isHigh() || getMotorPlus().isHigh() ) {
					//Send mail alert
					log.error( "The drawer should be open and is still always running!!!!" );
					stop();
				}else {
					log.info("The security for {} stay silent." , getName() );
				}
			}
		} ).start();
		
	}

	public void stop() {
		super.stopAll();
	}
	
}
