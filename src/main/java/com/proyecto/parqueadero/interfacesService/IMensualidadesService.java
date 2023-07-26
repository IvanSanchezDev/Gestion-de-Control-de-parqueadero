package com.proyecto.parqueadero.interfacesService;

import java.util.List;



import com.proyecto.parqueadero.modelo.Mensualidad;

public interface IMensualidadesService {
	
	public List<Mensualidad> findAll();
	public void save(Mensualidad mensualidad);
	public Mensualidad findOne(Long id);
	public void delete(Long id);
	public Integer cantidadMensualidadesCarro();
	public Integer cantidadMensualidadesMoto();
	public Double ingresosMensualidadCarro();
	public Double ingresosMensualidadMoto();
	public Double ingresosTotalesMensualidades();

}
