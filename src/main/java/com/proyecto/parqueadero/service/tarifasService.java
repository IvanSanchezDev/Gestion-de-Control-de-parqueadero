package com.proyecto.parqueadero.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.parqueadero.dao.ITarifaDao;
import com.proyecto.parqueadero.interfacesService.ITarifasService;
import com.proyecto.parqueadero.modelo.Tarifa;

@Service
public class tarifasService implements ITarifasService{
	
	
	@Autowired
	private ITarifaDao tarifaDao;

	@Override
	@Transactional(readOnly=true)
	public List<Tarifa> findAll() {
		// TODO Auto-generated method stub
		return (List<Tarifa>) tarifaDao.findAll();
	}

	@Override
	@Transactional
	public void save(Tarifa tarifa) {
		tarifaDao.save(tarifa);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Tarifa findOne(Long id) {
		// TODO Auto-generated method stub
		return tarifaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		tarifaDao.deleteById(id);
		
	}

}
