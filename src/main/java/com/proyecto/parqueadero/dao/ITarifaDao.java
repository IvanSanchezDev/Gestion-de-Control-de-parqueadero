package com.proyecto.parqueadero.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.parqueadero.modelo.Tarifa;



@Repository
public interface ITarifaDao extends CrudRepository<Tarifa, Long>{
	
	

	 @Query(value="SELECT valor_hora FROM tarifas where tipo_vehiculo='Automovil'", nativeQuery=true)
	 public int tarifaCarro();
	 
	 @Query(value="SELECT valor_hora FROM tarifas where tipo_vehiculo='Motocicleta'", nativeQuery=true)
	 public int tarifaMoto();
	 
	 @Query(value="SELECT valor_mes FROM tarifas where tipo_vehiculo='Automovil'", nativeQuery=true)
	 public int tarifaMesCarro();
	 
	 @Query(value="SELECT valor_mes FROM tarifas where tipo_vehiculo='Motocicleta'", nativeQuery=true)
	 public int tarifaMesMoto();
}
