package com.pc.departamento.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pc.departamento.domain.model.Servidor;
import com.pc.departamento.domain.model.Carreiras;
import com.pc.departamento.domain.model.CodigoUnidade;
import java.util.List;

@Repository
public interface ServidorRepository extends JpaRepository<Servidor, Long>{
	List<Servidor> findByCodigo(CodigoUnidade codigo);
	
	@Query("select count(*) from Servidor p where p.carreira = ?1")
	Long countByCarreira(Carreiras carreira);
	
}
