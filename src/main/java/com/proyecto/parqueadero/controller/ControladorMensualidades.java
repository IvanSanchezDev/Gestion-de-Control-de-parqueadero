package com.proyecto.parqueadero.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.parqueadero.interfacesService.IMensualidadesService;

import com.proyecto.parqueadero.modelo.Mensualidad;

@Controller
public class ControladorMensualidades {

	@Autowired
	private IMensualidadesService mensualidadService;

	@RequestMapping(value = "/crearMensualidad")
	public String crearListar(Map<String, Object> model) {
		try {

			Mensualidad mensualidad = new Mensualidad();
			model.put("mensualidad", mensualidad);
			model.put("titulo", "Mensualidades");
			model.put("mensualidades", mensualidadService.findAll());

		} catch (Exception e) {
			System.out.println("Error:  " + e.getMessage());
		}

		return "mensualidades";
	}

	@RequestMapping(value = "/guardarMensualidad", method = RequestMethod.POST)
	public String guardar(@Valid Mensualidad mensualidad, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Mensualidades");
			return "mensualidades";
		}

		mensualidadService.save(mensualidad);
		status.setComplete();
		flash.addFlashAttribute("success", "mensualidad creada correctamente");
		return "redirect:crearMensualidad";
	}

	@RequestMapping(value = "/editarMensualidad/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash, SessionStatus status) {
		

		Mensualidad mensualidad = null;
		if (id > 0) {
			mensualidad = mensualidadService.findOne(id);
			
		} else {
			
			return "mensualidades";
		}

		
		model.put("mensualidad", mensualidad);
		model.put("titulo", "Editar Mensualidad");
		model.put("showModal", true);
		//status.setComplete();
		//flash.addFlashAttribute("success", "Por favor edite la informacion");
		return "mensualidades";

	}

	
}
