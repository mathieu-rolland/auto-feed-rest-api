package com.perso.feed.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import com.perso.feed.material.RaspiPinProvider;
import com.perso.feed.material.emulator.IPinProvider;
import com.perso.feed.material.emulator.impl.GpioControllerEmulator;
import com.perso.feed.material.emulator.impl.PinProviderEmulator;
import com.perso.feed.service.BoxService;
import com.perso.feed.service.ErrorService;
import com.perso.feed.service.SoundPlayerService;
import com.perso.feed.tasks.DrawerOpenTask;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

@Configuration
public class AutoFeedBoxConfig {

	@Value("${auto-feed.tasks.sound.repeat}")
	private int nbRepeat;
	
	@Bean
	@Scope( scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON )
	@Autowired
	public BoxContext createContext(	GpioController gpio , 
										IPinProvider pinProvider ) {
		BoxContext context = new BoxContext();
		context.setupGpio( gpio , pinProvider);
		
		return context;
	}

	@Bean
	@Autowired
	public DrawerOpenTask sheduledConfiguration( ThreadPoolTaskScheduler scheduler, BoxService boxService , BoxContext context, SoundPlayerService player , ErrorService errorService ) {
		DrawerOpenTask drawer1OpenTask = new DrawerOpenTask(boxService  , player , errorService , context.getDrawer1() , nbRepeat );
		DrawerOpenTask drawer2Opentask = new DrawerOpenTask(boxService  , player , errorService , context.getDrawer2() , nbRepeat);
		
		scheduler.schedule(drawer1OpenTask, new CronTrigger("0 * * * * *"));
		
		scheduler.schedule(drawer2Opentask, new CronTrigger("30 * * * * *"));
		
		return drawer1OpenTask;
	}
	
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
