package com.proyecto.parqueadero.interfacesService;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyecto.parqueadero.modelo.Vehiculos;

public interface IVehiculosService {

	public List<Vehiculos> listar();// LISTAR VEHICULOS

	public Page<Vehiculos> listar(Pageable pageable);// LiStaR con paginacion

	public Page<Vehiculos> findByPlacaStartsWith(String placa, Pageable pageable);// LiStaR con paginacion FILTRAR PLACA

	public Page<Vehiculos> findByFecha(String fecha, Pageable pageable);

	public Optional<Vehiculos> listarId(int id); // LISTAR id

	public void save(Vehiculos v); // GUARDAR

	public void delete(int id); // ELIMINAR

	public double cerrarCaja();
	
	public Double ingresosCarro();
	
	public Double ingresosMoto();
	
	public Integer cantidadCarros();
	
	public Integer cantidadMotos();
	
	public Integer vehiculosActivos();

	public Vehiculos traerUltimoVehiculo();

}
