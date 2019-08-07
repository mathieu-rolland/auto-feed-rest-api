package com.perso.feed.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import com.perso.feed.material.RaspiPinProvider;
import com.perso.feed.material.emulator.IPinProvider;
import com.perso.feed.material.emulator.impl.GpioControllerEmulator;
import com.perso.feed.material.emulator.impl.PinProviderEmulator;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

@Configuration
public class AutoFeedBoxConfig {

	@Bean
	@Scope( scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON )
	@Autowired
	public BoxContext createContext( GpioController gpio , IPinProvider pinProvider ) {
		BoxContext context = new BoxContext();
		context.setupGpio( gpio , pinProvider );
		return context;
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
