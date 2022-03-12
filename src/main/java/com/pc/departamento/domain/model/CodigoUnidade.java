package com.pc.departamento.domain.model;

public enum CodigoUnidade {
	DEPP("Sede"),
	PUMA("PUMA"),
	DEAJEC("DEAJEC"),
	DEPLANI("Deplan I"),
	DEPLANII("Deplan II"),
	DEPLANIII("Deplan III"),
	DEPLANIV("Deplan IV"),
	REGIONALCENTRO("Regional Centro"),
	CENTROI("1ª Delegacia - Centro"),
	CENTROII("1ª Delegacia - Centro"),
	CENTROIII("1ª Delegacia - Centro"),
	CENTROIV("1ª Delegacia - Centro");
	
	private String descricao;
	
	CodigoUnidade (String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
