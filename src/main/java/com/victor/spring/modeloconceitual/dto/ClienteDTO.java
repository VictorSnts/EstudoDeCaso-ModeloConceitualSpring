package com.victor.spring.modeloconceitual.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.victor.spring.modeloconceitual.domain.Cliente;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message = "O preencimento do campo 'nome' é obrigatorio.")
	@Length(min = 5, max = 80, message = "Este campo 'nome' ter entre 5 e 80 caracteres")
	private String nome;
	@NotEmpty(message = "O preencimento do campo 'email' é obrigatorio.")
	@Email(message = "O email é invalido")
	private String email;
	@NotEmpty(message = "O preencimento do campo 'cpfOuCnpj' é obrigatorio.")
	@Length(min = 5, max = 80, message = "Este campo 'cpfOuCnpj' ter entre 11 e 14 caracteres")

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
