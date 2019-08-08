package com.perso.feed.converter.dto;

import com.perso.feed.material.exception.MaterialDtoConvertException;

public interface IDTOConverter<O,D> {

	public O generateFromDTO( D dto ) throws MaterialDtoConvertException;
	public D generateDTO( O object );
	
}
