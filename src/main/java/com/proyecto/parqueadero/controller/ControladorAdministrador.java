package com.proyecto.parqueadero.controller;




import java.util.Collection;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.parqueadero.interfacesService.IVehiculosService;
import com.proyecto.parqueadero.modelo.Vehiculos;
import com.proyecto.parqueadero.paginator.PageRender;

@Controller

public class ControladorAdministrador {
	
	protected final Log logger=LogFactory.getLog(this.getClass());
	
	@Autowired
	private IVehiculosService service;

	
	 @GetMapping("historialAdmin")
	 public String listarByPlaca(@RequestParam(name="page", defaultValue="0") int page, Model model, @Param("PalabraClave") String palabraClave) {
			
		 Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		 
		 if(hasRole("ROLE_ADMIN")) {
			 logger.info("Hola ".concat(auth.getName().concat(" tienes acceso")));
		 }else {
			 logger.info("Hola ".concat(auth.getName().concat(" NO tienes acceso")));
		 }
		 
		 Pageable pageRequest=PageRequest.of(page, 11, Sort.by("estado"));
			
			Page<Vehiculos> listaVehiculos=service.findByPlacaStartsWith(palabraClave, pageRequest);
			
			PageRender<Vehiculos> pageRender=new PageRender<>("/historialAdmin", listaVehiculos);
			
			model.addAttribute("vehiculo", listaVehiculos);
			model.addAttribute("palabraClave", palabraClave);
			model.addAttribute("page", pageRender);
			return "admin/historialAdmin";
	}
	 
	 @GetMapping("historialAdminFechas")
	 public String listarByFecha(@RequestParam(name="page", defaultValue="0") int page, Model model, @Param("fecha") String fecha) {
			Pageable pageRequest=PageRequest.of(page, 11, Sort.by("estado"));
			
			Page<Vehiculos> listaVehiculos=service.findByFecha(fecha, pageRequest);
			
			PageRender<Vehiculos> pageRender=new PageRender<>("/historialAdmin", listaVehiculos);
			
			model.addAttribute("vehiculo", listaVehiculos);
			model.addAttribute("fecha", fecha);
			model.addAttribute("page", pageRender);
			return "admin/historialAdmin";
	}
	 
	 
	 @GetMapping("/admin/estadisticas")
	 public String reportes() {
		 return "admin/estadisticas";
	 }
	 
	 private boolean hasRole(String role) {
		 SecurityContext context=SecurityContextHolder.getContext();
		 if(context==null) {
			 return false;
		 }
		 
		 Authentication auth=context.getAuthentication();
		 
		 if(auth==null) {
			 return false;
		 }
		 
		 Collection<? extends GrantedAuthority> authorities=auth.getAuthorities();
		 
		 return authorities.contains(new SimpleGrantedAuthority(role));
		 
		 
	 }
	
	 
	 
	
	

}
