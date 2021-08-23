package com.victor.spring.modeloconceitual.dto;

import java.io.Serializable;

import com.victor.spring.modeloconceitual.domain.Categoria;

public class CategoriaDTO implements Serializable { // Serve para definir somente os dados que precisamos para realizar
													// algumas operacoes basicas no sistema
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;

	public CategoriaDTO() {
		super();
	}

	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
		}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
