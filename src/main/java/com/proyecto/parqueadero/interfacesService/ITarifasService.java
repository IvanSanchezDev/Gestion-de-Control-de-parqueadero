package com.proyecto.parqueadero.interfacesService;

import java.util.List;

import com.proyecto.parqueadero.modelo.Tarifa;

public interface ITarifasService {
	public List<Tarifa> findAll();
	public void save(Tarifa tarifa);
	public Tarifa findOne(Long id);
	public void delete(Long id);

}
