package com.perso.feed.converter.dto.impl;

import org.springframework.stereotype.Service;

import com.perso.feed.converter.dto.IDTOConverter;
import com.perso.feed.material.exception.MaterialDtoConvertException;
import com.perso.feed.model.Flash;
import com.perso.feed.model.dto.FlashDTO;

@Service
public class FlashDTOConverter implements IDTOConverter<Flash, FlashDTO> {

	@Override
	public Flash generateFromDTO(FlashDTO dto) throws MaterialDtoConvertException {
		throw new MaterialDtoConvertException();
	}

	@Override
	public FlashDTO generateDTO(Flash object) {
		return new FlashDTO( object.isStarted() );
	}

}
