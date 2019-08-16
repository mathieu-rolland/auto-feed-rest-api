package com.perso.feed.converter.dto.impl;

import org.springframework.stereotype.Service;

import com.perso.feed.converter.dto.IDTOConverter;
import com.perso.feed.material.exception.MaterialDtoConvertException;
import com.perso.feed.model.ErrorDescription;
import com.perso.feed.model.dto.ErrorDescriptionDTO;

@Service
public class ErrorDescriptionDTOConverter implements IDTOConverter<ErrorDescription, ErrorDescriptionDTO> {

	@Override
	public ErrorDescription generateFromDTO(ErrorDescriptionDTO dto) throws MaterialDtoConvertException {
		ErrorDescription errorDesc = new ErrorDescription();
		errorDesc.setErrorCode( dto.getErrorCode() );
		errorDesc.setErrorMessage( dto.getErrorMessage() );
		return errorDesc;
	}

	@Override
	public ErrorDescriptionDTO generateDTO(ErrorDescription object) {
		ErrorDescriptionDTO dto = new ErrorDescriptionDTO();
		if( object != null ) {
			dto.setErrorCode( object.getErrorCode() );
			dto.setErrorMessage( object.getErrorMessage() );
		}
		return dto;
	}

}
