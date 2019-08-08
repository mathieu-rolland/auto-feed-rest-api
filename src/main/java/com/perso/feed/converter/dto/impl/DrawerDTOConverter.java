package com.perso.feed.converter.dto.impl;

import org.springframework.stereotype.Service;

import com.perso.feed.converter.dto.IDTOConverter;
import com.perso.feed.material.exception.MaterialDtoConvertException;
import com.perso.feed.model.Drawer;
import com.perso.feed.model.dto.DrawerDTO;

@Service
public class DrawerDTOConverter implements IDTOConverter<Drawer, DrawerDTO>{

	@Override
	public Drawer generateFromDTO(DrawerDTO dto) throws MaterialDtoConvertException {
		throw new MaterialDtoConvertException();
	}

	@Override
	public DrawerDTO generateDTO(Drawer object) {
		DrawerDTO dto = new DrawerDTO();
		dto.setState( object.getState() );
		dto.setName( object.getName() );
		return dto;
	}

}
