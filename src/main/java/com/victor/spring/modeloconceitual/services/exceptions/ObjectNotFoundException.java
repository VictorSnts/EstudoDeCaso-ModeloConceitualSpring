package com.victor.spring.modeloconceitual.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String erro) {
		super(erro);
	}

	public ObjectNotFoundException(String erro, Throwable causa) {
		super(erro, causa);
	}
}
