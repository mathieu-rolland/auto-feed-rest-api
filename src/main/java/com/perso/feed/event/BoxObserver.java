package com.perso.feed.event;

import com.perso.feed.model.Drawer;

public interface BoxObserver {

	public void receivedEvent( BoxEvent event, Drawer drawer );
	
}
