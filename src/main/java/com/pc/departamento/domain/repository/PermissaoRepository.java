package com.pc.departamento.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pc.departamento.domain.model.Permissao;

@Repository
public interface PermissaoRepository extends CrudRepository<Permissao, Long>{
	
	@Query("select p from Permissao p where p.permissao = ?1")
	Permissao findByPermissao(String permissao);
}