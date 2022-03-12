package com.pc.departamento.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pc.departamento.domain.model.Usuario;
import com.pc.departamento.domain.service.CadastroUsuarioService;

@Controller
public class PerfilController {	
	
	@Autowired
	private CadastroUsuarioService usuarioService;
	
	@RequestMapping("/perfil")
	public ModelAndView perfil() {
		ModelAndView view = new ModelAndView("perfil");
		 
		Usuario usuario = usuarioService.getUsuario();
		
		view.addObject("usuarioLogado", usuario);
		view.addObject("usuario", usuario);
		
		return view;
	}
	
	@RequestMapping(value = "/atualizarSenha", method = RequestMethod.POST)
	public String atualizarSenha(@RequestParam("currentPassword") String currentPassword, 
			@RequestParam("newPassword") String newPassword,
			RedirectAttributes attributes) {
		
		Usuario usuario = usuarioService.getUsuario();
		
		if(BCrypt.checkpw(currentPassword, usuario.getPassword())) {
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			usuario.setSenha(encoder.encode(newPassword));
			
			usuarioService.atualizarSenha(usuario);
			
			attributes.addFlashAttribute("msg", "Senha alterada com sucesso");
			
			return "redirect:/perfil";
		}else {
			
			attributes.addFlashAttribute("senhaerror", "Senha atual incorreta!");
			return "redirect:/perfil";
		}
	}
	
}
