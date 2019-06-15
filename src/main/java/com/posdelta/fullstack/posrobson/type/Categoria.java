package com.posdelta.fullstack.posrobson.type;

public enum Categoria {
	
	hATCH("HATCH"), 
	SEDAN("SEDAN"), 
	UTILITARIO("UTILITÁRIO"), 
	ESPORTIVO("ESPOSTIVO");

	private String descricao;

	private Categoria(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
