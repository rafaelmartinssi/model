package com.pc.departamento.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Servidor {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "* Campo nome é obrigatório")
	private String nome;
	@NotEmpty(message = "* Campo masp é obrigatório")
	private String masp;
	@NotNull(message = "* Campo carreira é obrigatório")
	@Enumerated(EnumType.STRING)
	private Carreiras carreira;
	@Enumerated(EnumType.STRING)
	private CodigoUnidade codigo;

}
