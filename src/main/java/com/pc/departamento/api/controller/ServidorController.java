package com.pc.departamento.api.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pc.departamento.domain.model.Carreiras;
import com.pc.departamento.domain.model.Servidor;
import com.pc.departamento.domain.model.Usuario;
import com.pc.departamento.domain.repository.ServidorRepository;
import com.pc.departamento.domain.service.CadastroServidorService;
import com.pc.departamento.domain.service.CadastroUsuarioService;
import com.pc.departamento.domain.service.JasperService;

@Controller
@RequestMapping("/servidores")
public class ServidorController {
	
	public static final String SERVIDOR = "servidor";
	public static final String SERVIDOR_REGISTRO = "servidor-registro";
	
	@Autowired
	private CadastroServidorService servidorService;
	@Autowired
	private ServidorRepository servidorRepository;
	@Autowired
	private CadastroUsuarioService usuarioService;
	@Autowired
	private JasperService jasperService;
	@RequestMapping
	public ModelAndView cadastro() {
		ModelAndView view = new ModelAndView(SERVIDOR);
		
		view.addObject(new Servidor());
		
		Usuario usuario = usuarioService.getUsuario();
		view.addObject("usuarioLogado", usuario);
		view.addObject("servidores", servidorRepository.findByCodigo(usuario.getCodigo()));
		
		return view;
	}
	
	@RequestMapping("{id}")
	public ModelAndView atualizar(@PathVariable("id") Servidor servidor) {
		ModelAndView view = new ModelAndView(SERVIDOR);
		
		view.addObject(servidor);
		
		Usuario usuario = usuarioService.getUsuario();
		view.addObject("usuarioLogado", usuario);
		view.addObject("servidores", servidorRepository.findByCodigo(usuario.getCodigo()));
		
		return view;
	}
	
	@RequestMapping("/registro")
	public ModelAndView consultar() {
		ModelAndView view = new ModelAndView(SERVIDOR_REGISTRO);
		
		Usuario usuario = usuarioService.getUsuario();
		view.addObject("usuarioLogado", usuario);
		view.addObject("servidores", servidorRepository.findAll());
		
		return view;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Servidor servidor, Errors errors, RedirectAttributes attributes) {
		
		if(errors.hasErrors()) {
			attributes.addFlashAttribute("msgerror", "Erro ao salvar servidor, verifique o preenchimento dos campos");			
			return "redirect:/servidores";
		}
		
		servidorService.salvar(servidor);
		attributes.addFlashAttribute("msg", "Servidor salvo com sucesso");
		
		return "redirect:/servidores";
	}
	
	@RequestMapping(value = "{id}" ,method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {

		servidorService.excluir(id);
		
		attributes.addFlashAttribute("msg", "Registro excluído com sucesso!");

		return "redirect:/servidores";
	}
	
	@RequestMapping(value = "/relatorio")
	public void relatorio(HttpServletResponse response,
			@RequestParam(name = "codigo", required = false) Long codigo) throws IOException {
		String code = "servidor";
		
		jasperService.addParams("UNIDADE", codigo == null ? null : codigo);
		
		byte[] bytes = jasperService.exportarPDF(code);
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		response.setHeader("Content-disposition", "inline; filename=relatorio-servidor.pdf");
		response.getOutputStream().write(bytes);
	}
	
	@ModelAttribute
	public List<Carreiras> Carreiras(){
		return Arrays.asList(Carreiras.values());
	}
	
}
