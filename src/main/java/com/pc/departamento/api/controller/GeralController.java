package com.pc.departamento.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pc.departamento.domain.model.Carreiras;
import com.pc.departamento.domain.model.Geral;
import com.pc.departamento.domain.model.Usuario;
import com.pc.departamento.domain.repository.ServidorRepository;
import com.pc.departamento.domain.service.CadastroUsuarioService;

@Controller
public class GeralController {
	
	@Autowired 
	private ServidorRepository servidorRepository;
	@Autowired
	private CadastroUsuarioService usuarioService;
	
	@RequestMapping(value = "/geral")
	public ModelAndView estatisticas() {
		ModelAndView view = new ModelAndView("geral");
		
		Geral geral = new Geral();
		
		geral.setTotalInvestigador(servidorRepository.countByCarreira(Carreiras.INVESTIGADOR));
		geral.setTotalDelegado(servidorRepository.countByCarreira(Carreiras.DELEGADO));
		geral.setTotalEscrivao(servidorRepository.countByCarreira(Carreiras.ESCRIVAO));
		geral.setTotalTecnico(servidorRepository.countByCarreira(Carreiras.TECNICO));
		geral.setTotalAnalista(servidorRepository.countByCarreira(Carreiras.ANALISTA));
		
		Usuario usuario = usuarioService.getUsuario();
		view.addObject("usuarioLogado", usuario);
		view.addObject(geral);
		
		return view;
	}
	
}
