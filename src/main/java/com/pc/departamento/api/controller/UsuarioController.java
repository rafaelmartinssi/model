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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pc.departamento.domain.model.Carreiras;
import com.pc.departamento.domain.model.CodigoUnidade;
import com.pc.departamento.domain.model.Permissao;
import com.pc.departamento.domain.model.Usuario;
import com.pc.departamento.domain.repository.PermissaoRepository;
import com.pc.departamento.domain.repository.UsuarioRepository;
import com.pc.departamento.domain.service.CadastroUsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	public static final String USUARIO_CADASTRO = "usuario-cadastro";
	public static final String USUARIO_REGISTRO = "usuario-registro";
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CadastroUsuarioService usuarioService;
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	
	@RequestMapping
	public ModelAndView cadastro() {
		ModelAndView view = new ModelAndView(USUARIO_CADASTRO);
		
		Usuario usuario = usuarioService.getUsuario();
		view.addObject("usuarioLogado", usuario);
		view.addObject(new Usuario());
		
		return view;
	}
	
	@RequestMapping("/registros")
	public ModelAndView gerir() {
		ModelAndView view = new ModelAndView(USUARIO_REGISTRO);
		
		Usuario usuario = usuarioService.getUsuario();
		view.addObject("usuarioLogado", usuario);
		view.addObject("usuarios", usuarioRepository.findAll());
		
		return view;
	}
	
	@RequestMapping("{id}")
	public ModelAndView atualizar(@PathVariable("id") Usuario usuario) {
		ModelAndView view = new ModelAndView(USUARIO_CADASTRO);
		
		Usuario usuarioLogado = usuarioService.getUsuario();
		view.addObject("usuarioLogado", usuarioLogado);
		view.addObject(usuario);
		
		return view;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Usuario usuario, Errors errors, RedirectAttributes attributes) {
		
		if(errors.hasErrors()) {
			attributes.addFlashAttribute("msgerror", "Erro ao salvar usuário, verifique o preenchimento dos campos");	
			return "redirect:/usuarios";
		}
		
		usuarioService.salvar(usuario);	
		attributes.addFlashAttribute("msg", "Usuário salvo com sucesso");
		
		return "redirect:/usuarios";
	}
	
	@RequestMapping(value = "{id}" ,method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		usuarioService.excluir(id);
		
		attributes.addFlashAttribute("msg", "Registro excluído com sucesso!");
		return "redirect:/usuarios/registros";
	}
	
	//GESTÃO DE PERMISSÕES
	@RequestMapping("/info")
	public ModelAndView info(@RequestParam("id") Long id) {
		ModelAndView view = new ModelAndView("usuario-info");
			
		Usuario usuario = usuarioRepository.findById(id).get();
		view.addObject("usuario", usuario);
		
		Usuario usuarioLogado = usuarioService.getUsuario();
		view.addObject("usuarioLogado", usuarioLogado);
		
		return view;
	}
	
	@RequestMapping(value = "/autorizar", method = RequestMethod.PUT)
	private ModelAndView autorizar(@RequestParam("id") Long id,
			@RequestParam("authorization") String authorization) {
		ModelAndView view = new ModelAndView("redirect:info");
		
		Usuario usuario = usuarioRepository.findById(id).get();
		
		Permissao permissao = permissaoRepository.findByPermissao(authorization);
		
		if(!usuario.getPermissoes().contains(permissao)) {
			usuarioService.autorizar(usuario, permissao);
			view.addObject("id", usuario.getId());
		}else {
			view.addObject("id", usuario.getId());
		}
		
		return view;
	}
	
	@RequestMapping(value = "/desautorizar")
	public ModelAndView desautorizar(@RequestParam("idPermissao") Long idPermissao,
			@RequestParam("idUsuario") Long idUsuario) {
		ModelAndView view = new ModelAndView("redirect:info");
		
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		
		Permissao permissao = permissaoRepository.findById(idPermissao).get();
		
		usuarioService.desautorizar(usuario, permissao);
 		
		view.addObject("id", usuario.getId());
		
		return view;
	}
	//FIM GESTÃO DE PERMISSÕES

	@ModelAttribute
	public List<CodigoUnidade> codigosUnidades(){
		return Arrays.asList(CodigoUnidade.values());
	}

	@ModelAttribute
	public List<Carreiras> Carreiras(){
		return Arrays.asList(Carreiras.values());
	}
}