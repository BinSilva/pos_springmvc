package com.posdelta.fullstack.posrobson.type;

public enum Sexo {
	
	MASCULINO("MASCULINO"),
	FEMININO("FEMININO");
	
	private String descricao;

	private Sexo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
