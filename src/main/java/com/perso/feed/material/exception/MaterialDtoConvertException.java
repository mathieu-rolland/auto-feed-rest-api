package com.perso.feed.material.exception;

public class MaterialDtoConvertException extends Exception{

	private static final long serialVersionUID = 1L;

	public MaterialDtoConvertException() {
		super("The physical material should not be serialized from dto");
	}
	
}
