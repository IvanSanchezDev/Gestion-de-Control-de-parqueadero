package com.proyecto.parqueadero.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.stereotype.Repository;

import com.proyecto.parqueadero.modelo.Vehiculos;

@Repository
public interface IVehiculos extends PagingAndSortingRepository<Vehiculos, Integer> {

	// SELECCIONAR UN VEHICULO POR LA PLACA
	@Query(value = "SELECT * FROM vehiculo WHERE placa=:placa", nativeQuery = true)
	List<Vehiculos> vehiculo(String placa);

	// TRAE EL VEHICUKI POR LA PLACA Y EL ESTADO DISPOINBLE
	@Query(value = "SELECT * FROM vehiculo WHERE placa=:placa And  estado='Disponible'", nativeQuery = true)
	Optional<Vehiculos> traerVehiculo(String placa);

	// TRAER EL VEHICULO QUE SE RETIRA
	@Query(value = "select * from vehiculo where estado='No Disponible' order by hora_salida desc limit 1", nativeQuery = true)
	public Vehiculos ultimoVehiculo();

	@Modifying()
	@Query(value = "UPDATE vehiculo v SET hora_salida=?1, estado='No Disponible', valor_pagado=?2 WHERE v.placa=?3 AND v.estado='Disponible'", nativeQuery = true)
	@Transactional
	void updateSalida(Date hora_salida, double valor_pagado, String placa);

	
	//CERRAR CAJA
	@Query(value = "SELECT SUM(valor_pagado) FROM vehiculo WHERE  DATE(hora_salida)=DATE(now())", nativeQuery = true)
	double ingresos();

	// ENCONTRAR PLACA Y LISTAR ADMIN
	public Page<Vehiculos> findByPlacaStartsWith(String placa, Pageable pageable);

	// ENCONTRAR LOS VEHICULOS SEGUN LA FECHA
	@Query(value = "SELECT * FROM vehiculo WHERE DATE(hora_entrada)=:hora_entrada", nativeQuery = true)
	public Page<Vehiculos> findByFecha(String hora_entrada, Pageable pageable);

	// CONTAR CARROS
	@Query(value = "SELECT COUNT(*) FROM vehiculo WHERE tipo_vehiculo='carro' and DATE(hora_entrada)=DATE(now())", nativeQuery = true)
	Optional<Integer>cantidadCarros();

	// CONTAR MOTOS
	@Query(value = "SELECT COUNT(*) FROM vehiculo WHERE tipo_vehiculo='moto' and DATE(hora_entrada)=DATE(now())", nativeQuery = true)
	Optional<Integer>cantidadMotos();

	// INGRESOS CARROS
	@Query(value = "SELECT SUM(valor_pagado) FROM vehiculo WHERE tipo_vehiculo='carro' and DATE(hora_salida)=DATE(now())", nativeQuery = true)
	Optional<Double>ingresosCarros();

	// INGRESOS MOTOS
	@Query(value = "SELECT SUM(valor_pagado) FROM vehiculo WHERE tipo_vehiculo='moto' and  DATE(hora_salida)=DATE(now())", nativeQuery = true)
	Optional<Double>ingresosMotos();
	
	//TRAER LISTA POR ORDEN PARA EL DASHBOARD
	@Query(value="SELECT * FROM `vehiculo` ORDER BY hora_entrada DESC LIMIT 0,5", nativeQuery=true)
	public List<Vehiculos> listaVehiculos();
	
	//VEHICULOS ACTIVOS
	@Query(value="SELECT COUNT(*) FROM vehiculo WHERE estado='Disponible'", nativeQuery=true)
	Optional<Integer> vehiculosActivos();
	
	

}
