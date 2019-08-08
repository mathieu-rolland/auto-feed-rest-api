package com.perso.feed.converter.dto.impl;

import org.springframework.stereotype.Service;

import com.perso.feed.converter.dto.IDTOConverter;
import com.perso.feed.material.exception.MaterialDtoConvertException;
import com.perso.feed.model.dto.GpioPinDigitalOutputDTO;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

@Service
public class GpioPinDigitalOutputDTOConverter implements IDTOConverter<GpioPinDigitalOutput, GpioPinDigitalOutputDTO>{

	@Override
	public GpioPinDigitalOutput generateFromDTO(GpioPinDigitalOutputDTO dto) throws MaterialDtoConvertException {
		throw new MaterialDtoConvertException();
	}

	@Override
	public GpioPinDigitalOutputDTO generateDTO(GpioPinDigitalOutput object) {
		GpioPinDigitalOutputDTO dto = new GpioPinDigitalOutputDTO();
		dto.setState( object.getState().getName() );
		return dto;
	}

}
