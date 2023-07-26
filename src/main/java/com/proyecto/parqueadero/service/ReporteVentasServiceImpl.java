package com.proyecto.parqueadero.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.parqueadero.commons.JasperReportManager;
import com.proyecto.parqueadero.dao.ReporteVehiculosDTO;

import com.proyecto.parqueadero.interfacesService.IReporteVehiculosService;

import net.sf.jasperreports.engine.JRException;

@Service
public class ReporteVentasServiceImpl implements IReporteVehiculosService {

	@Autowired
	private JasperReportManager reportManager;

	@Autowired
	private DataSource dataSource;

	@Override
	public ReporteVehiculosDTO obtenerReporte(Map<String, Object> params, String FechaInicio, String FechaFin)
			throws JRException, IOException, SQLException {
		String fileName = "reporte_de_vehiculos";
		ReporteVehiculosDTO dto = new ReporteVehiculosDTO();
		String extension =".pdf";
		dto.setFileName(fileName + extension);
		ByteArrayOutputStream stream = reportManager.export(fileName, params, FechaInicio, FechaFin,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		dto.setStream(new ByteArrayInputStream(bs));
		dto.setLength(bs.length);

		return dto;
	}

}
