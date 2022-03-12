package com.pc.departamento.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pc.departamento.domain.model.CodigoUnidade;
import com.pc.departamento.domain.model.Unidade;
import com.pc.departamento.domain.model.Usuario;
import com.pc.departamento.domain.repository.UnidadeRepository;
import com.pc.departamento.domain.service.CadastroUnidadeService;
import com.pc.departamento.domain.service.CadastroUsuarioService;

@Controller
@RequestMapping("/unidades")
public class UnidadeController {
	
	public static final String UNIDADE = "unidade";
	public static final String CATALOGO = "catalogo";
	
	@Autowired
	private CadastroUnidadeService unidadeService;
	@Autowired
	private UnidadeRepository unidadeRepository;
	@Autowired
	private CadastroUsuarioService usuarioService;

	@RequestMapping
	public ModelAndView cadastro() {
		ModelAndView view = new ModelAndView(UNIDADE);
		
		view.addObject(new Unidade());
		
		Usuario usuario = usuarioService.getUsuario();
		view.addObject("usuarioLogado", usuario);
		view.addObject("unidades", unidadeRepository.findAll());
		
		return view;
	}
	
	@RequestMapping("/catalogo")
	public ModelAndView consultar() {
		ModelAndView view = new ModelAndView(CATALOGO);
		
		Usuario usuario = usuarioService.getUsuario();
		view.addObject("usuarioLogado", usuario);
		view.addObject("unidades", unidadeRepository.findAll());
		
		return view;
	}
	
	@RequestMapping("{id}")
	public ModelAndView atualizar(@PathVariable("id") Unidade unidade) {
		ModelAndView view = new ModelAndView(UNIDADE);
		
		view.addObject(unidade);
		
		Usuario usuario = usuarioService.getUsuario();
		view.addObject("usuarioLogado", usuario);
		view.addObject("unidades", unidadeRepository.findAll());
		
		return view;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Unidade unidade, Errors errors, RedirectAttributes attributes) {
		
		if(errors.hasErrors()) {
			attributes.addFlashAttribute("msgerror", "Erro ao salvar unidade, verifique o preenchimento dos campos");
			return "redirect:/unidades";
		}
		
		unidadeService.salvar(unidade);
		
		attributes.addFlashAttribute("msg", "Unidade salva com sucesso");
		
		return "redirect:/unidades";
	}
	
	@RequestMapping(value = "{id}" ,method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {

		unidadeService.excluir(id);
		
		attributes.addFlashAttribute("msg", "Registro excluído com sucesso!");

		return "redirect:/unidades";
	}
	
	@ModelAttribute
	public List<CodigoUnidade> codigosUnidades(){
		return Arrays.asList(CodigoUnidade.values());
	}
}
