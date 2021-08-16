package com.victor.spring.modeloconceitual.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Fisica"),
	PESSOAJURIDICA(2, "Pessoa Juridica");

	private Integer id;
	private String descricao;
	
	private TipoCliente(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	public static TipoCliente toEnum(Integer id) {
		if(id == null) {
			return null;
		}
		
		for(TipoCliente tipo : TipoCliente.values()) {
			if(id.equals(tipo.getId())) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("Id invalido: "+ id);
	}
}
