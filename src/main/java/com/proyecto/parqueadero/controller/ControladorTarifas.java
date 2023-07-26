package com.proyecto.parqueadero.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.parqueadero.interfacesService.ITarifasService;

import com.proyecto.parqueadero.modelo.Tarifa;

@Controller
public class ControladorTarifas {
	
	@Autowired 
	private ITarifasService tarifasService;
	
	@GetMapping("/tarifas")
	public String listarTarifas(Map<String, Object> model) {
		
		Tarifa tarifa = new Tarifa();
		model.put("tarifa", tarifa);
		
		model.put("tarifas", tarifasService.findAll());
		
		return "tarifas";
	}
	
	@RequestMapping(value = "/guardarTarifa", method = RequestMethod.POST)
	public String guardar(@Valid Tarifa tarifa, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Tarifas");
			return "tarifas";
		}

		tarifasService.save(tarifa);
		status.setComplete();
		flash.addFlashAttribute("success", "Tarifa creada correctamente");
		return "redirect:tarifas";
	}
	
	@RequestMapping(value = "/editarTarifa/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		Tarifa tarifa = null;
		if (id > 0) {
			tarifa = tarifasService.findOne(id);
		} else {
			return "tarifas";
		}
		model.put("tarifa", tarifa);
		model.put("titulo", "Editar Tarifa");
		model.put("showModal", true);
		return "tarifas";

	}

	@RequestMapping(value = "/eliminarTarifa/{id}")
	public String delete(@PathVariable(value = "id") Long id,  RedirectAttributes flash, SessionStatus status) {
		if (id > 0) {
			tarifasService.delete(id);
			status.setComplete();
			flash.addFlashAttribute("error", "Tarifa eliminada correctamente");
		}
		return "redirect:/tarifas";

	}

}
