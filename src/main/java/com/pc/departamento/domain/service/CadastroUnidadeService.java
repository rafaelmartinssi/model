package com.pc.departamento.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.departamento.domain.model.Unidade;
import com.pc.departamento.domain.repository.UnidadeRepository;

@Service
public class CadastroUnidadeService {
	
	@Autowired
	private UnidadeRepository unidadeRepository;
	
	public void salvar(Unidade unidade) {
		unidadeRepository.save(unidade);
	}
	
	public void excluir(Long id) {
		unidadeRepository.deleteById(id);
	}
	
}
