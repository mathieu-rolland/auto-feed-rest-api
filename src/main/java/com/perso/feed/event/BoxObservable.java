package com.perso.feed.event;

public interface BoxObservable {

	public void sendEvent();
	public void addObserver( BoxObserver box );
	public void removeObserver( BoxObserver box );
	
}
