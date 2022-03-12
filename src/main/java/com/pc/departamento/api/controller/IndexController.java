package com.pc.departamento.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pc.departamento.domain.model.Usuario;
import com.pc.departamento.domain.service.CadastroUsuarioService;

@Controller
public class IndexController {	
	
	@Autowired
	private CadastroUsuarioService usuarioService;
	
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("index");

		Usuario usuario = usuarioService.getUsuario();
		view.addObject("usuarioLogado", usuario);
		return view;
	}	
}
