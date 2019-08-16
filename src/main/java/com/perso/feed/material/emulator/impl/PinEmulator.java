package com.perso.feed.material.emulator.impl;

import java.util.EnumSet;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinEdge;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;

public class PinEmulator implements Pin{

	private int address;
	
	private String name;
	
	private String provider;
	
	public PinEmulator(String name) {
		this.name = name;
	}
	
	@Override
	public int compareTo(Pin o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getProvider() {
		// TODO Auto-generated method stub
		return provider;
	}

	@Override
	public int getAddress() {
		return address;
	}

	@Override
	public String getName() {
		return name;
	}

	public String setName(String name) {
		return this.name = name;
	}
	
	@Override
	public EnumSet<PinMode> getSupportedPinModes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supportsPinPullResistance() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EnumSet<PinPullResistance> getSupportedPinPullResistance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supportsPinEdges() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supportsPinEvents() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EnumSet<PinEdge> getSupportedPinEdges() {
		// TODO Auto-generated method stub
		return null;
	}

}
