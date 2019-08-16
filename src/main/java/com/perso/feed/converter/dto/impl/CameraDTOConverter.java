package com.perso.feed.converter.dto.impl;

import org.springframework.stereotype.Service;

import com.perso.feed.converter.dto.IDTOConverter;
import com.perso.feed.material.exception.MaterialDtoConvertException;
import com.perso.feed.model.Camera;
import com.perso.feed.model.dto.CameraDTO;

@Service
public class CameraDTOConverter implements IDTOConverter<Camera, CameraDTO> {

	@Override
	public Camera generateFromDTO(CameraDTO dto) throws MaterialDtoConvertException {
		throw new MaterialDtoConvertException();
	}

	@Override
	public CameraDTO generateDTO(Camera object) {
		CameraDTO dto = new CameraDTO();
		dto.setState( object.getState() );
		return dto;
	}

}
