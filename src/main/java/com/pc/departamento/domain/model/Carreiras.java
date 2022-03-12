package com.pc.departamento.domain.model;

public enum Carreiras {
	INVESTIGADOR("Investigador"),
	ESCRIVAO("Escrivão"),
	DELEGADO("Delegado"),
	TECNICO("Técnico Assistente"),
	ANALISTA("Analista"),
	PERITO("Perito Criminal"),
	LEGISTA("Médico Legista");
	
	private String descricao;
	
	Carreiras (String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
