package com.victor.spring.modeloconceitual.resource.exception;

import java.io.Serializable;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer status;
	private String menssagem;
	private Long instante;

	public StandardError(Integer status, String menssagem, Long instante) {
		super();
		this.status = status;
		this.menssagem = menssagem;
		this.instante = instante;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMenssagem() {
		return menssagem;
	}

	public void setMenssagem(String menssagem) {
		this.menssagem = menssagem;
	}

	public Long getInstante() {
		return instante;
	}

	public void setInstante(Long instante) {
		this.instante = instante;
	}

}
