package com.pc.departamento.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pc.departamento.domain.model.Veiculo;
import com.pc.departamento.domain.model.CodigoUnidade;
import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{
	List<Veiculo> findByCodigo(CodigoUnidade codigo);
}
