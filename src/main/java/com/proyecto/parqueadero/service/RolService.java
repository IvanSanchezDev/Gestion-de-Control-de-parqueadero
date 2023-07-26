package com.proyecto.parqueadero.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.parqueadero.auth.enums.RolNombre;
import com.proyecto.parqueadero.interfaces.IRol;
import com.proyecto.parqueadero.interfacesService.IRolService;
import com.proyecto.parqueadero.modelo.Rol;

@Service
public class RolService implements IRolService {
	
	@Autowired
	private IRol rolRepository;

	@Override
	public List<Rol> findAll() {
		// TODO Auto-generated method stub
		return rolRepository.findAll();
	}

	@Override
	public void save(Rol rol) {
		rolRepository.save(rol);
		
	}

	@Override
	public Optional<Rol> findOne(Long id) {
		// TODO Auto-generated method stub
		return rolRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		rolRepository.deleteById(id);
		
	}

	@Override
	public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
		// TODO Auto-generated method stub
		return rolRepository.findByRolNombre(rolNombre);
	}

	@Override
	public boolean exitsById(Long id) {
		// TODO Auto-generated method stub
		return rolRepository.existsById(id);
	}

}
