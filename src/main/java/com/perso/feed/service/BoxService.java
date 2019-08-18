package com.perso.feed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.perso.feed.model.Box;
import com.perso.feed.model.Drawer;
import com.perso.feed.model.ReturnCodeEnum;
import com.perso.feed.model.SecurityDrawer;

@Service
public class BoxService {

	@Autowired
	private Box box;
	
	@Value("${auto-feed.security.max}")
	private int maxTimeBeforeAlert;
	
	public ReturnCodeEnum openDrawer( int number ) {
		Drawer drawer = box.getDrawers().get(number);
		if( drawer != null ) {
			return new SecurityDrawer( maxTimeBeforeAlert , drawer ).open();
		}
		return ReturnCodeEnum.UNKNOWN_DRAWER;
	}
	
	public ReturnCodeEnum closeDrawer( int number ) throws InterruptedException {
		Drawer drawer = box.getDrawers().get(number);
		if( drawer != null ) {
			return new SecurityDrawer( maxTimeBeforeAlert , drawer ).close();
		}
		return ReturnCodeEnum.UNKNOWN_DRAWER;
			
	}
		
}
