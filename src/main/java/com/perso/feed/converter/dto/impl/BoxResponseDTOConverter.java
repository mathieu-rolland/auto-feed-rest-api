package com.perso.feed.converter.dto.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perso.feed.converter.dto.IDTOConverter;
import com.perso.feed.material.exception.MaterialDtoConvertException;
import com.perso.feed.model.BoxResponse;
import com.perso.feed.model.dto.BoxResponseDTO;

@Service
public class BoxResponseDTOConverter implements IDTOConverter<BoxResponse, BoxResponseDTO> {

	@Autowired
	private BoxStateDTOConverter boxStateDTOConverter;
	
	@Autowired
	private ErrorDescriptionDTOConverter errorDescriptionConverter;

	@Override
	public BoxResponse generateFromDTO(BoxResponseDTO dto) throws MaterialDtoConvertException {
		BoxResponse response = new BoxResponse();
		response.setBoxState( boxStateDTOConverter.generateFromDTO(dto.getBoxState()) );
		response.setError( errorDescriptionConverter.generateFromDTO(dto.getErrorDescription()) );
		return response;
	}

	@Override
	public BoxResponseDTO generateDTO(BoxResponse object) {
		BoxResponseDTO dto = new BoxResponseDTO();
		dto.setBoxState( boxStateDTOConverter.generateDTO(object.getBoxState()) );
		if( object.getError() != null ) {
			dto.setErrorDescription( errorDescriptionConverter.generateDTO(object.getError()) );
		}
		return dto;
	}
	
	
	
}
