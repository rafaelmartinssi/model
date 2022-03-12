package com.pc.departamento.domain.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario implements UserDetails{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "* Campo masp é obrigatório")
	private String masp;
	@NotEmpty(message = "* Campo nome é obrigatório")
	private String nome;
	@NotEmpty(message = "* Campo sobrenome é obrigatório")
	private String sobrenome;
	private String senha;
	@NotNull(message = "* Campo unidade é obrigatório")
	@Enumerated(EnumType.STRING)
	private CodigoUnidade codigo;
	@NotNull(message = "* Campo carreira é obrigatório")
	@Enumerated(EnumType.STRING)
	private Carreiras carreira;
	
	@ManyToMany(fetch = FetchType.EAGER) 
	@JoinTable(name = "usuario_permissao",
			joinColumns = @JoinColumn(name = "usuario_id",
			table = "usuario"), 
	inverseJoinColumns = @JoinColumn(name = "permissao_id",
			table = "permissao"))
	private List<Permissao> permissoes;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return permissoes;
	}
	
	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return masp;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}