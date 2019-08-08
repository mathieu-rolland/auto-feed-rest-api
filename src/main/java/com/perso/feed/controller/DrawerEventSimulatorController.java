package com.perso.feed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perso.feed.config.BoxContext;
import com.perso.feed.config.SpringProfile;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/emulator")
@Profile( value = { SpringProfile.EMULATOR } )
@Slf4j
public class DrawerEventSimulatorController {

	@Autowired
	private BoxContext boxContext;
	
	@RequestMapping("closedEvent")
	public void drawerEventClosed() {
		log.info("ClosedEvent called");
		boxContext.getClosingListener().handleGpioPinDigitalStateChangeEvent(null);
	}
	
	@RequestMapping("openedEvent")
	public void drawerEventOpened() {
		log.info("OpenedEvent called");
		boxContext.getOpeningListener().handleGpioPinDigitalStateChangeEvent(null);
	}
	
}
