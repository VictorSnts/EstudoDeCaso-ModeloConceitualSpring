package com.victor.spring.modeloconceitual.resource.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> erros = new ArrayList<>();

	public ValidationError(Integer status, String menssagem, Long instante) {
		super(status, menssagem, instante);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getErros() {
		return erros;
	}

	public void addError(String fieldName, String msg) {
		erros.add(new FieldMessage(fieldName, msg));
	}

}
