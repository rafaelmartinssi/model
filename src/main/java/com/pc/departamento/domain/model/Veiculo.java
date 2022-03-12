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
public class Veiculo {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "* Campo marca é obrigatório")
	private String marca;
	@NotEmpty(message = "* Campo modelo é obrigatório")
	private String modelo;
	@NotEmpty(message = "* Campo placa é obrigatório")
	private String placa;
	@NotEmpty(message = "* Campo placa de segurança é obrigatório")
	private String seguranca;
	@NotNull(message = "* Campo tipo é obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoVeiculo tipo;
	@Enumerated(EnumType.STRING)
	private CodigoUnidade codigo;
}
