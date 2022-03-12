package com.pc.departamento.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pc.departamento.domain.model.Permissao;
import com.pc.departamento.domain.model.Usuario;
import com.pc.departamento.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void salvar(Usuario usuario) {
		
		if(usuario.getId() != null) {
			Usuario usuarioAtual = usuarioRepository.findById(usuario.getId()).get();
			usuario.setSenha(usuarioAtual.getSenha());
			usuario.setPermissoes(usuarioAtual.getPermissoes());
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		usuario.setSenha(encoder.encode(usuario.getMasp()));
		
		usuarioRepository.save(usuario);
	}
	
	public void atualizarSenha(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public void excluir(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	public Usuario getUsuario() {
		Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication(); 
		Usuario usuario = usuarioRepository.findByMasp(authentication.getName());
		return usuario;
	}
	
	@Transactional
	public void autorizar(Usuario usuario, Permissao permissao) {
		usuario.getPermissoes().add(permissao);
	}
	
	@Transactional
	public void desautorizar(Usuario usuario, Permissao permissao) {
		usuario.getPermissoes().remove(permissao);
	}
	
	
}
