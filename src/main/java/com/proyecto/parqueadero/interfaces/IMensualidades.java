package com.proyecto.parqueadero.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.proyecto.parqueadero.modelo.Mensualidad;

public interface IMensualidades extends PagingAndSortingRepository<Mensualidad, Long> {

	// CANTIDAD MENSUALIDADES
	@Query(value = "SELECT COUNT(*) FROM mensualidades WHERE fecha_inicio=DATE(now()) AND tipo_vehiculo='carro'", nativeQuery = true)
	Optional<Integer> cantidadMensualidades();

	// CANTIDAD MENSUALIDADES
	@Query(value = "SELECT COUNT(*) FROM mensualidades WHERE fecha_inicio=DATE(now()) AND tipo_vehiculo='moto'", nativeQuery = true)
	Optional<Integer> cantidadMensualidadesMoto();

	@Procedure(value = "actualizarEstadoMensualidad")
	public void actualizarMensualidad();

}
