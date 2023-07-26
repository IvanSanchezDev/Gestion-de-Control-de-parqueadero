package com.proyecto.parqueadero.controller;


import java.util.List;
import java.util.Map;
import java.util.Optional;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.parqueadero.interfacesService.IRolService;
import com.proyecto.parqueadero.interfacesService.IUsuarioService;
import com.proyecto.parqueadero.modelo.Rol;
import com.proyecto.parqueadero.modelo.Usuario;

@Controller
public class ControladorUsuario {

	@Autowired
	private IUsuarioService service;

	@Autowired
	private IRolService serviceRol;

	@GetMapping("/listarUsuarios")
	public String listar(Model model) {

		model.addAttribute("usuario", new Usuario());
		model.addAttribute("rol", serviceRol.findAll());
		model.addAttribute("usuarios", service.findAll());
		model.addAttribute("titulo", "Usuarios");

		return "usuarios";
	}

	@PostMapping("/admin/guardarUsuario")
	public String save(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {

		try {
			service.save(usuario);
			status.setComplete();
			flash.addFlashAttribute("success", "Usuario creado correctamente");
			return "redirect:/listarUsuarios";
		} catch (DataIntegrityViolationException e) {
			result.rejectValue("username", "error.usuario", "Ya existe un usuario con ese username");
			return "usuarios";
		}

	}

	@GetMapping("/admin/editarUsuario/{id}")
	public String editar(@PathVariable long id, Map<String, Object> model) {
		Optional<Usuario> usuario = null;
		if (id > 0) {
			usuario = service.findOne(id);
		} else {
			return "usuarios";
		}
		
		List<Rol>roles=serviceRol.findAll();
		

		model.put("usuario", usuario);
		model.put("rol", roles);
		model.put("titulo", "Editar Usuario");
		model.put("showModal", true);
		return "usuarios";
	}

	@GetMapping("/admin/eliminarUsuario/{id}")
	public String delete(Model model, @PathVariable Long id, RedirectAttributes flash, SessionStatus status) {

		if (id > 0) {
			service.delete(id);
			status.setComplete();
			flash.addFlashAttribute("error", "Usuario eliminado correctamente");
		}

		return "redirect:/listarUsuarios";
	}

}
