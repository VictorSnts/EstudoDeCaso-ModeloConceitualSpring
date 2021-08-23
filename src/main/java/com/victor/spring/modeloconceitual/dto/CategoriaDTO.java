package com.victor.spring.modeloconceitual.dto;

import java.io.Serializable;

import com.victor.spring.modeloconceitual.domain.Categoria;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class CategoriaDTO implements Serializable { // Serve para definir somente os dados que precisamos para realizar
													// algumas operacoes basicas no sistema
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message = "O preencimento deste campo é obrigatorio.")
	@Length(min=5, max=80, message = "Este campo precisa ter entre 5 e 80 caracteres")
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
