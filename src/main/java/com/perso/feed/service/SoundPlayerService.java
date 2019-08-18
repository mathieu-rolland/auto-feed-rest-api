package com.perso.feed.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SoundPlayerService {

	@Value("${auto-feed.sound.path}")
	private String soundPath;
	
	private boolean isPlaying = false;
	
	private Clip clip;
	
	@PostConstruct
	private void setup() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		AudioInputStream stream = AudioSystem.getAudioInputStream( new File(soundPath) );
		clip = AudioSystem.getClip();
		clip.open(stream);
	}
	
	public void playSound() throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {
		
		if( !isPlaying ) {
		
			isPlaying = true;
			
			clip.start();
			log.info("The sound was played");
			while( clip.isRunning() ) {
				Thread.sleep( (clip.getMicrosecondLength() - clip.getMicrosecondPosition()) / 1000 );
			}
			clip.setMicrosecondPosition(0l);
			
			isPlaying = false;
			
		}
	}
	
}
