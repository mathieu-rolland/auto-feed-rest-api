package com.perso.feed.converter.dto.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perso.feed.converter.dto.IDTOConverter;
import com.perso.feed.material.exception.MaterialDtoConvertException;
import com.perso.feed.model.BoxState;
import com.perso.feed.model.Camera;
import com.perso.feed.model.Drawer;
import com.perso.feed.model.dto.BoxStateDTO;
import com.perso.feed.model.dto.CameraDTO;
import com.perso.feed.model.dto.DrawerDTO;

@Service
public class BoxStateDTOConverter implements IDTOConverter<BoxState, BoxStateDTO>{

	@Autowired
	private IDTOConverter<Camera, CameraDTO> cameroDtoConverter;
	
	@Autowired
	private IDTOConverter<Drawer, DrawerDTO> drawerDtoConverter;
	
//	@Autowired
//	private IDTOConverter<GpioPinDigitalOutput, GpioPinDigitalOutputDTO> gpioDtoConverter;
	
	@Override
	public BoxState generateFromDTO(BoxStateDTO dto) throws MaterialDtoConvertException {
		throw new MaterialDtoConvertException();
	}

	@Override
	public BoxStateDTO generateDTO(BoxState object) {
		BoxStateDTO dto = new BoxStateDTO();
		dto.setCamera( cameroDtoConverter.generateDTO(object.getCamera()) );
		dto.setDrawer1( drawerDtoConverter.generateDTO(object.getDrawer1()) );
		dto.setDrawer2( drawerDtoConverter.generateDTO(object.getDrawer2()) );
		//dto.setLed( gpioDtoConverter.generateDTO(object.getLedState()) );
		return dto;
	}

}
