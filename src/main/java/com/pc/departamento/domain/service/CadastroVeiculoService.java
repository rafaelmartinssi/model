package com.pc.departamento.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.departamento.domain.model.Usuario;
import com.pc.departamento.domain.model.Veiculo;
import com.pc.departamento.domain.repository.VeiculoRepository;

@Service
public class CadastroVeiculoService {
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	@Autowired
	private CadastroUsuarioService usuarioService;
	
	public void salvar(Veiculo veiculo) {
		Usuario usuario = usuarioService.getUsuario();
		veiculo.setCodigo(usuario.getCodigo());
		veiculoRepository.save(veiculo);
	}
	
	public void excluir(Long id) {
		veiculoRepository.deleteById(id);
	}
}
