package com.proyecto.parqueadero.service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.parqueadero.dao.IMensualidadDao;
import com.proyecto.parqueadero.dao.ITarifaDao;
import com.proyecto.parqueadero.interfaces.IMensualidades;
import com.proyecto.parqueadero.interfacesService.IMensualidadesService;
import com.proyecto.parqueadero.modelo.Mensualidad;

@Service
public class mensualidadesService implements IMensualidadesService{
	
	@Autowired
	private IMensualidadDao mensualidadDao;
	
	@Autowired
	private IMensualidades data;
	
	@Autowired
	private ITarifaDao dataTarifa;

	@Override
	@Transactional(readOnly=true)
	public List<Mensualidad> findAll() {
		// TODO Auto-generated method stub
		return mensualidadDao.findAll();
	}

	@Override
	@Transactional
	public void save(Mensualidad mensualidad) {
		LocalDate hoy = LocalDate.now();
		mensualidad.setFecha_inicio(hoy);
		mensualidad.setFecha_fin(hoy.plusMonths(1));
		mensualidad.setHabilitado(true);
		mensualidadDao.save(mensualidad);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Mensualidad findOne(Long id) {
		return mensualidadDao.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		mensualidadDao.delete(id);
		
	}

	@Override
	public Integer cantidadMensualidadesCarro() {
			Optional<Integer>informacionCantidadCarro=data.cantidadMensualidades();
		
		Integer cantidadCarro;
		if(informacionCantidadCarro.isEmpty()) {
			return cantidadCarro=0;
		}else {
			cantidadCarro= data.cantidadMensualidades().get();
		}
		return cantidadCarro;
	}
	
	@Override
	public Integer cantidadMensualidadesMoto() {
		Optional<Integer>informacionCantidadMoto=data.cantidadMensualidadesMoto();
		
		Integer cantidadMoto;
		if(informacionCantidadMoto.isEmpty()) {
			return cantidadMoto=0;
		}else {
			cantidadMoto= data.cantidadMensualidadesMoto().get();
		}
		return cantidadMoto;
	}

	@Override
	public Double ingresosMensualidadCarro() {
		Double cantidadCarro=(double) cantidadMensualidadesCarro();
			
		Double ingresosCarro= cantidadCarro * dataTarifa.tarifaMesCarro();
		return ingresosCarro;
	}

	@Override
	public Double ingresosMensualidadMoto() {
		Double cantidadMoto=(double) cantidadMensualidadesMoto();
		
		Double ingresosMoto= cantidadMoto * dataTarifa.tarifaMesMoto();
		return ingresosMoto;
	}

	@Override
	public Double ingresosTotalesMensualidades() {
		// TODO Auto-generated method stub
		return ingresosMensualidadMoto()+ingresosMensualidadCarro();
	}

	




}
