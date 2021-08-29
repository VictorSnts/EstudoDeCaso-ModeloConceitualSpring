package com.victor.spring.modeloconceitual.resource.exception;

import java.io.Serializable;

/***
 * Classe criada para carregar o nome do campo e a mensagemd e erro
 * @author victor
 *
 */
public class FieldMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private String filedName;
	private String msg;

	public FieldMessage() {
		super();
	}

	public FieldMessage(String filedName, String msg) {
		super();
		this.filedName = filedName;
		this.msg = msg;
	}

	public String getFiledName() {
		return filedName;
	}

	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
