package com.perso.feed.material.emulator.impl;

import com.perso.feed.material.emulator.IPinProvider;
import com.pi4j.io.gpio.Pin;

public class PinProviderEmulator implements IPinProvider {

	@Override
	public Pin getPin00() {
		return new PinEmulator( "00" );
	}
	
	@Override
	public Pin getPin01() {
		return new PinEmulator( "01" );
	}

	@Override
	public Pin getPin02() {
		return new PinEmulator( "02" );
	}
	
	@Override
	public Pin getPin20() {
		return new PinEmulator( "20" );
	}

	@Override
	public Pin getPin21() {
		return new PinEmulator( "21" );
	}

	@Override
	public Pin getPin22() {
		return new PinEmulator( "22" );
	}

	@Override
	public Pin getPin23() {
		return new PinEmulator( "23" );
	}

	@Override
	public Pin getPin24() {
		return new PinEmulator( "24" );
	}

	@Override
	public Pin getPin25() {
		return new PinEmulator( "25" );
	}

	@Override
	public Pin getPin26() {
		return new PinEmulator( "26" );
	}

}
