package com.perso.feed.material;

import com.perso.feed.material.emulator.IPinProvider;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class RaspiPinProvider implements IPinProvider {

	@Override
	public Pin getPin00() {
		return RaspiPin.GPIO_00;
	}

	@Override
	public Pin getPin01() {
		return RaspiPin.GPIO_01;
	}

	@Override
	public Pin getPin02() {
		return RaspiPin.GPIO_02;
	}
	
	@Override
	public Pin getPin20() {
		return RaspiPin.GPIO_20;
	}

	@Override
	public Pin getPin21() {
		return RaspiPin.GPIO_21;
	}

	@Override
	public Pin getPin22() {
		return RaspiPin.GPIO_22;
	}

	@Override
	public Pin getPin23() {
		return RaspiPin.GPIO_23;
	}

	@Override
	public Pin getPin24() {
		return RaspiPin.GPIO_24;
	}

	@Override
	public Pin getPin25() {
		return RaspiPin.GPIO_25;
	}

}
