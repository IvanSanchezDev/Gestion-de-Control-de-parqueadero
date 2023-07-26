package com.proyecto.parqueadero.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.parqueadero.dao.ReporteVehiculosDTO;

import com.proyecto.parqueadero.interfacesService.IReporteVehiculosService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("report")
public class ControladorReportes {

	@Autowired
	private IReporteVehiculosService reporteVehiculosService;

	@GetMapping("cantidadVehiculos")
	public ResponseEntity<Resource> download(@RequestParam Map<String, Object> params,
			@Param("FechaIncio") String FechaInicio, @Param("FechaFin") String FechaFin)
			throws JRException, IOException, SQLException {
		ReporteVehiculosDTO dto = reporteVehiculosService.obtenerReporte(params, FechaInicio, FechaFin);
		InputStreamResource streamResource = new InputStreamResource(dto.getStream());
		MediaType mediaType = null;

		mediaType = MediaType.APPLICATION_PDF;

		return ResponseEntity.ok().header("Content-Disposition", "inline: filename=\"" + dto.getFileName() + "\"")
				.contentLength(dto.getLength()).contentType(mediaType).body(streamResource);
	}
}
