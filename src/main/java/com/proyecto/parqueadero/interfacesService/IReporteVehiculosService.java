package com.proyecto.parqueadero.interfacesService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.proyecto.parqueadero.dao.ReporteVehiculosDTO;

import net.sf.jasperreports.engine.JRException;

public interface IReporteVehiculosService {
	
	ReporteVehiculosDTO obtenerReporte(Map<String, Object> params, String FechaInicio, String FechaFin) throws JRException, IOException, SQLException;

}
