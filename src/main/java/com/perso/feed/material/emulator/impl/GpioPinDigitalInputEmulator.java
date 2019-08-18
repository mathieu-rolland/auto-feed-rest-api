package com.perso.feed.material.emulator.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinShutdown;
import com.pi4j.io.gpio.GpioProvider;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.trigger.GpioTrigger;

public class GpioPinDigitalInputEmulator implements GpioPinDigitalInput{

	private boolean high;
	
	private PinState state;
	
	private Pin pin;
	
	private String name;
	
	public GpioPinDigitalInputEmulator(Pin pin, PinState state, String name) {
		
		this.state = state;
		this.pin = pin;
		this.name = name;
		this.high = true;
	}
	
	@Override
	public boolean isHigh() {
		// TODO Auto-generated method stub
		return high;
	}

	@Override
	public boolean isLow() {
		// TODO Auto-generated method stub
		return !high;
	}

	@Override
	public PinState getState() {
		// TODO Auto-generated method stub
		return state;
	}

	@Override
	public boolean isState(PinState state) {
		return this.state.equals(state);
	}

	@Override
	public GpioProvider getProvider() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pin getPin() {
		return pin;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setTag(Object tag) {
	}

	@Override
	public Object getTag() {
		return null;
	}

	@Override
	public void setProperty(String key, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasProperty(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getProperty(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProperty(String key, String defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeProperty(String key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearProperties() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void export(PinMode mode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void export(PinMode mode, PinState defaultState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unexport() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isExported() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setMode(PinMode mode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PinMode getMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMode(PinMode mode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPullResistance(PinPullResistance resistance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PinPullResistance getPullResistance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPullResistance(PinPullResistance resistance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<GpioPinListener> getListeners() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addListener(GpioPinListener... listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener(List<? extends GpioPinListener> listeners) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasListener(GpioPinListener... listener) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(GpioPinListener... listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(List<? extends GpioPinListener> listeners) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAllListeners() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GpioPinShutdown getShutdownOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setShutdownOptions(GpioPinShutdown options) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setShutdownOptions(Boolean unexport) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setShutdownOptions(Boolean unexport, PinState state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setShutdownOptions(Boolean unexport, PinState state, PinPullResistance resistance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setShutdownOptions(Boolean unexport, PinState state, PinPullResistance resistance, PinMode mode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<GpioTrigger> getTriggers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTrigger(GpioTrigger... trigger) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTrigger(List<? extends GpioTrigger> triggers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTrigger(GpioTrigger... trigger) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTrigger(List<? extends GpioTrigger> triggers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAllTriggers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasDebounce(PinState state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getDebounce(PinState state) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setDebounce(int debounce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDebounce(int debounce, PinState... state) {
		// TODO Auto-generated method stub
		
	}

}
