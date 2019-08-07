package com.perso.feed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perso.feed.config.BoxContext;
import com.perso.feed.model.Drawer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoxService {

	@Autowired
	private BoxContext boxContext;
	
	@Autowired
	private DrawerService drawerService;
	
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
	
}
