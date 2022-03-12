package com.pc.departamento.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.departamento.domain.model.Servidor;
import com.pc.departamento.domain.model.Usuario;
import com.pc.departamento.domain.repository.ServidorRepository;

@Service
public class CadastroServidorService {
	
	@Autowired
	private ServidorRepository servidorRepository;
	@Autowired
	private CadastroUsuarioService usuarioService;
	
	public void salvar(Servidor servidor) {
		Usuario usuario = usuarioService.getUsuario();
		servidor.setCodigo(usuario.getCodigo());
		servidorRepository.save(servidor);
	}
	
	public void excluir(Long id) {
		servidorRepository.deleteById(id);
	}
}
