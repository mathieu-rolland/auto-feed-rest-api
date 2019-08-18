package com.perso.feed.tasks;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.perso.feed.event.BoxEvent;
import com.perso.feed.event.BoxObserver;
import com.perso.feed.model.Box;
import com.perso.feed.model.BoxTask;
import com.perso.feed.model.Drawer;
import com.perso.feed.model.SecurityDrawer;
import com.perso.feed.service.ErrorService;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class DrawerOpenTask implements BoxTask , BoxObserver{

	private String taskName;
	
	private int nbRepeat;
	
	private Box box;
	
	private int drawerNumber;
	
	private int maxTimeBeforeAlert;
	
	public DrawerOpenTask( Box box, int drawerNumber, ErrorService errorService, int nbRepeat, int maxTimeBeforeAlert) {
		this.nbRepeat = nbRepeat;
		this.box = box;
		this.drawerNumber = drawerNumber;
		this.maxTimeBeforeAlert = maxTimeBeforeAlert;
	}
	
	@Override
	public void run() {
		try {
			log.info("Start task for drawer {}" , drawerNumber);
			new SecurityDrawer( maxTimeBeforeAlert , box.getDrawers().get(drawerNumber) ).open();
			box.getOpeningListener().addObserver(this);
			log.info("End task for drawer {}" , drawerNumber);
		}catch (Exception e) {
			//TODO : errorService.sendMail( e );
		}
	}
	
	@Override
	public void receivedEvent(BoxEvent event, Drawer drawer) {
		try {
			try {
				for( int i = 0 ; i < nbRepeat ; i++ ) {
					log.info("Play sound {}" , i);
					//this method is synchronized
					box.playSound();
				}
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException | InterruptedException e) {
				log.error("Error on playing sound!" , e);
				throw e;
			}
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException | InterruptedException e) {
			e.printStackTrace();
			//TODO : faire la gestion des erreurs
		}
	}

}
