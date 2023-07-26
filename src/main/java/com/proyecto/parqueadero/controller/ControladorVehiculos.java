package com.proyecto.parqueadero.controller;

import java.io.IOException;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lowagie.text.DocumentException;
import com.proyecto.parqueadero.interfacesService.IMensualidadesService;
import com.proyecto.parqueadero.interfacesService.IVehiculosService;
import com.proyecto.parqueadero.mail.JavaMailService;
import com.proyecto.parqueadero.modelo.Vehiculos;
import com.proyecto.parqueadero.paginator.PageRender;

import com.proyecto.parqueadero.reportes.cierreCajaPdf;
import com.proyecto.parqueadero.reportes.ticketSalidaPdf;

@Controller
@RequestMapping
public class ControladorVehiculos {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private IVehiculosService service;
	
	@Autowired
	private IMensualidadesService mensualidadService;
	
	

	@Autowired
	private JavaMailService emailSender;

	@GetMapping("/listarVehiculos")
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 11, Sort.by("estado"));
		Page<Vehiculos> vehiculos = service.listar(pageRequest);

		PageRender<Vehiculos> pageRender = new PageRender<>("/listarVehiculos", vehiculos);
		model.addAttribute("vehiculo", vehiculos);
		model.addAttribute("page", pageRender);

		return "listarVehiculos";
	}

	@GetMapping("/newVehiculo")
	public String agregar(Map<String, Object> model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			logger.info("Hola usuario autenticado, tu username es: ".concat(auth.getName()) + " roles; "
					+ auth.getAuthorities());
		}

		Vehiculos vehiculo = new Vehiculos();
		model.put("vehiculo", vehiculo);
		model.put("titulo", "Registro de vehiculos");
		
		Vehiculos ultimoVehiculo = service.traerUltimoVehiculo();
		model.put("vehiculoo", ultimoVehiculo);
		
		int espacio = 40;
		int cantActivos = service.vehiculosActivos();
		int disponibilidad = espacio - cantActivos;
		model.put("disponibilidad", disponibilidad);

		return "registroVehiculos";
	}

	@PostMapping("/guardarVehiculo")
	public String save(@Valid Vehiculos vehiculo, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) throws DocumentException, IOException {

		model.addAttribute("titulo", "Registro de vehiculos");
		if (result.hasErrors()) {
			status.setComplete();
			flash.addFlashAttribute("error", "Por favor ingrese Nuevamente el vehiculo");
			return "redirect:newVehiculo";
		}

		service.save(vehiculo);
		

		return "redirect:newVehiculo";

	}

	@GetMapping("/sendEmail")
	public String sendEmail(Authentication auth)
			throws DocumentException, IOException, com.itextpdf.text.DocumentException {

		String usuario = auth.getName();
		Double ingresosCarro = service.ingresosCarro();
		Double ingresosMoto = service.ingresosMoto();
		Integer cantidadCarros = service.cantidadCarros();
		Integer cantidadMotos = service.cantidadMotos();
		
		
		
		Integer cantidadMensualidadCarro=mensualidadService.cantidadMensualidadesCarro();
		Double ingresosMensualidadCarro = mensualidadService.ingresosMensualidadCarro();
		
		Integer cantidadMensualidadMoto=mensualidadService.cantidadMensualidadesMoto();
		Double ingresosMensualidadMoto = mensualidadService.ingresosMensualidadMoto();
		
		
		Double valorTotal = service.cerrarCaja()+mensualidadService.ingresosTotalesMensualidades();
		cierreCajaPdf CdCaja = new cierreCajaPdf();
		CdCaja.generate(ingresosCarro, ingresosMoto, cantidadCarros, ingresosMensualidadCarro,cantidadMensualidadCarro,ingresosMensualidadMoto ,cantidadMensualidadMoto, cantidadMotos, valorTotal, usuario);
		emailSender.sendListEmail();

		return "redirect:/historialAdmin";
	}

	@GetMapping("/ticketSalida")
	public String ticketSalidPdf(Authentication auth) throws IOException, com.itextpdf.text.DocumentException {
		ticketSalidaPdf ticketSalida = new ticketSalidaPdf();
		Vehiculos ultimoVehiculo = service.traerUltimoVehiculo();
		String usuario = auth.getName();

		ticketSalida.generateTicketSalida(ultimoVehiculo.getPlaca(), ultimoVehiculo.getTipo_vehiculo(),
				ultimoVehiculo.getHora_entrada().toString(), ultimoVehiculo.getHora_salida().toString(), usuario);
		return "redirect:/newVehiculo";
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model) {

		/* GRAFICA */
		model.addAttribute("automovil", service.ingresosCarro());
		model.addAttribute("moto", service.ingresosMoto());

		/* LISTA */
		List<Vehiculos> listaVehiculos = service.listar();
		model.addAttribute("vehiculo", listaVehiculos);

		/* INFO */
		int espacio = 40;
		int cantActivos = service.vehiculosActivos();
		int disponibilidad = espacio - cantActivos;
		model.addAttribute("cantVehiculosActivos", cantActivos);
		model.addAttribute("cantAutomoviles", service.cantidadCarros());
		model.addAttribute("cantMotos", service.cantidadMotos());
		model.addAttribute("disponibilidad", disponibilidad);

		return "dashboard";
	}

}
