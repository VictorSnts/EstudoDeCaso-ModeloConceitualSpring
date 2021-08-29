package com.victor.spring.modeloconceitual.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.victor.spring.modeloconceitual.services.validation.ClienteInsert;

@ClienteInsert
public class NovoClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "O preencimento do campo 'nome' é obrigatorio.")
	@Length(min = 5, max = 80, message = "Este campo 'nome' ter entre 5 e 80 caracteres")
	private String nome;
	@NotEmpty(message = "O preencimento do campo 'email' é obrigatorio.")
	@Email(message = "O email é invalido")
	private String email;
	@NotEmpty(message = "O preencimento do campo 'cpfOuCnpj' é obrigatorio.")
	private String cpfOuCnpj;
	private Integer tipoCliente;
	
	@NotEmpty(message = "O preencimento do campo 'logradouro' é obrigatorio.")
	private String logradouro;
	private Integer numero;
	private String complemento;
	private String bairro;
	@NotEmpty(message = "O preencimento do campo 'cep' é obrigatorio.")
	private String cep;
	
	@NotEmpty(message = "O preencimento do campo 'tel1' é obrigatorio.")
	private String tel1;
	private String tel2;
	private String tel3;

	private Integer cidadeId;

	public NovoClienteDTO() {
		super();
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(Integer tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getTel3() {
		return tel3;
	}

	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

}
