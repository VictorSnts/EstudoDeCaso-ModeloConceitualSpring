package com.victor.spring.modeloconceitual.services.exceptions;

public class DataIntegrityException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String erro) {
		super(erro);
	}

	public DataIntegrityException(String erro, Throwable causa) {
		super(erro, causa);
	}
}
