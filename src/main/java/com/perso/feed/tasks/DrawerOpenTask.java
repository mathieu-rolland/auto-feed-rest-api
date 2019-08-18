package com.perso.feed.tasks;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import com.perso.feed.model.Drawer;
import com.perso.feed.service.BoxService;
import com.perso.feed.service.ErrorService;
import com.perso.feed.service.SoundPlayerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DrawerOpenTask implements Runnable{

	private BoxService boxService;
	
	private SoundPlayerService soundPlayerService;
	
	private ErrorService errorService;
	
	private ThreadPoolTaskScheduler scheduler;
	
	private int nbRepeat;
	
	private Drawer drawer;
	
	public DrawerOpenTask( BoxService boxService, SoundPlayerService player , ErrorService errorService, Drawer drawer, int nbRepeat) {
		this.boxService = boxService;
		this.soundPlayerService = player;
		this.errorService = errorService;
		this.drawer = drawer;
		this.nbRepeat = nbRepeat;
	}
	
	public DrawerOpenTask( Drawer drawer, int nbRepeat) {
		this.drawer = drawer;
		this.nbRepeat = nbRepeat;
	}
	
	@Override
	public void run() {
		try {
			log.info("Start task for drawer {}" , drawer.getNumber());
			boxService.openDrawer( drawer.getNumber() );
			try {
				for( int i = 0 ; i < nbRepeat ; i++ ) {
					log.info("Play sound {}" , i);
					//this method is synchronized
					soundPlayerService.playSound();
				}
				Thread.sleep( 1000 * 10 );
				boxService.closeDrawer( drawer.getNumber() );
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException | InterruptedException e) {
				log.error("Error on playing sound!" , e);
				throw e;
			}
			log.info("End task for drawer {}" , drawer.getNumber());
		}catch (Exception e) {
			errorService.sendMail( e );
		}
	}

}