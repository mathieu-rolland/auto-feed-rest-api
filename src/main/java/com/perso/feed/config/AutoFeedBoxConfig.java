package com.perso.feed.config;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import com.perso.feed.material.RaspiPinProvider;
import com.perso.feed.material.emulator.IPinProvider;
import com.perso.feed.material.emulator.impl.GpioControllerEmulator;
import com.perso.feed.material.emulator.impl.PinProviderEmulator;
import com.perso.feed.model.Box;
import com.perso.feed.service.ErrorService;
import com.perso.feed.tasks.DrawerOpenTask;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

@Configuration
public class AutoFeedBoxConfig {

	@Value("${auto-feed.tasks.sound.repeat}")
	private int nbRepeat;
	
	/*@Bean
	@Scope( scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON )
	@Autowired
	public BoxContext createContext(	GpioController gpio , 
										IPinProvider pinProvider ) {
		BoxContext context = new BoxContext();
		context.setupGpio( gpio , pinProvider);
		
		return context;
	}*/
	
	@Value("${auto-feed.sound.path}")
	private String soundPath;
	
	@Value("${auto-feed.camera.cmd}")
	private String cmd;
	
	@Value("${auto-feed.camera.cmd_force}")
	private String forceCameraStop;
	
	@Value("${auto-feed.security.max}")
	private int maxTimeBeforeAlert;
	
	@Bean
	public Box createBox(GpioController gpio , IPinProvider pinProvider, ThreadPoolTaskScheduler scheduler , ErrorService errorService) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		
		Box box = new Box();
		box.setupBox(gpio, pinProvider, maxTimeBeforeAlert);
		box.setupCamera(cmd, forceCameraStop);
		box.setupSound(soundPath);
		box.setScheduler(scheduler);
		box.addTask( new DrawerOpenTask( box , 1 , errorService , nbRepeat , maxTimeBeforeAlert ) , new CronTrigger("0 * * * * *") );
		
		//box.addTask( new DrawerOpenTask( box , 2 , errorService , nbRepeat , maxTimeBeforeAlert ) , new CronTrigger("0 * * * * *") );
		
		return box;
	}
	
	/*public DrawerOpenTask sheduledConfiguration( ThreadPoolTaskScheduler scheduler, BoxService boxService, SoundPlayerService player , ErrorService errorService ) {
		DrawerOpenTask drawer1OpenTask = new DrawerOpenTask(boxService  , player , errorService , context.getDrawer1() , nbRepeat );
		DrawerOpenTask drawer2Opentask = new DrawerOpenTask(boxService  , player , errorService , context.getDrawer2() , nbRepeat );
		
		scheduler.schedule(drawer1OpenTask, new CronTrigger("0 * * * * *"));
		scheduler.schedule(drawer2Opentask, new CronTrigger("30 * * * * *"));
		
		return drawer1OpenTask;
	}*/
	
	@Bean
	@Profile( value =  { SpringProfile.RASPBERRY, SpringProfile.DEV } )
	public GpioController getGpioControllerRaspberry() {
		return GpioFactory.getInstance();
	}
	
	@Bean
	@Profile( value = { SpringProfile.EMULATOR } )
	public GpioController getGpioControllerEmulator() {
		return new GpioControllerEmulator();
	}
	
	@Bean
	@Profile( value =  { SpringProfile.RASPBERRY, SpringProfile.DEV } )
	public IPinProvider pinRaspiProvider() {
		return new RaspiPinProvider();
	}
	
	@Bean
	@Profile( value =  { SpringProfile.EMULATOR } )
	public IPinProvider pinEmulatIPinProvider() {
		return new PinProviderEmulator();
	}
	
}
