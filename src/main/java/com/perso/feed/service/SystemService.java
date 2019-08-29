package com.perso.feed.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.pi4j.system.SystemInfo;

@Service
public class SystemService {

	
	public float getCpuTemperature() throws NumberFormatException, UnsupportedOperationException, IOException, InterruptedException {
		return SystemInfo.getCpuTemperature();
	}
	
	
}
