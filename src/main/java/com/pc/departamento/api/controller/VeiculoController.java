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

import com.pc.departamento.domain.model.TipoVeiculo;
import com.pc.departamento.domain.model.Usuario;
import com.pc.departamento.domain.model.Veiculo;
import com.pc.departamento.domain.repository.VeiculoRepository;
import com.pc.departamento.domain.service.CadastroUsuarioService;
import com.pc.departamento.domain.service.CadastroVeiculoService;

@Controller
@RequestMapping("/veiculos")
public class VeiculoController {
	
	public static final String FROTA = "frota";
	public static final String FROTA_REGISTRO = "frota-registro";
	
	@Autowired
	private CadastroVeiculoService veiculoService;	
	@Autowired
	private VeiculoRepository veiculoRepository;
	@Autowired
	private CadastroUsuarioService usuarioService;
	
	@RequestMapping
	public ModelAndView cadastro() {
		ModelAndView view = new ModelAndView(FROTA);
		
		view.addObject(new Veiculo());
		
		Usuario usuario = usuarioService.getUsuario();
		view.addObject("usuarioLogado", usuario);
		view.addObject("veiculos", veiculoRepository.findByCodigo(usuario.getCodigo()));
		
		return view;
	}
	
	@RequestMapping("/registro")
	public ModelAndView consultar() {
		ModelAndView view = new ModelAndView(FROTA_REGISTRO);
		
		Usuario usuario = usuarioService.getUsuario();
		view.addObject("usuarioLogado", usuario);
		view.addObject("veiculos", veiculoRepository.findAll());
		
		return view;
	}
	
	@RequestMapping("{id}")
	public ModelAndView atualizar(@PathVariable("id") Veiculo veiculo) {
		ModelAndView view = new ModelAndView(FROTA);
		
		view.addObject(veiculo);
		
		Usuario usuario = usuarioService.getUsuario();
		view.addObject("usuarioLogado", usuario);
		view.addObject("veiculos", veiculoRepository.findByCodigo(usuario.getCodigo()));
		
		return view;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Veiculo veiculo, Errors errors, RedirectAttributes attributes) {
		
		if(errors.hasErrors()) {
			attributes.addFlashAttribute("msgerror", "Erro ao salvar veículo, verifique o preenchimento dos campos");
			return "redirect:/veiculos";
		}
		
		veiculoService.salvar(veiculo);
		
		attributes.addFlashAttribute("msg", "Veículo salvo com sucesso");
		
		return "redirect:/veiculos";
	}
	
	@RequestMapping(value = "{id}" ,method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {

		veiculoService.excluir(id);
		
		attributes.addFlashAttribute("msg", "Registro excluído com sucesso!");

		return "redirect:/veiculos";
	}
	
	@ModelAttribute
	public List<TipoVeiculo> tipoVeiculo(){
		return Arrays.asList(TipoVeiculo.values());
	}
	
}
