package com.proyecto.parqueadero.interfacesService;

import java.util.List;
import java.util.Optional;

import com.proyecto.parqueadero.auth.enums.RolNombre;
import com.proyecto.parqueadero.modelo.Rol;


public interface IRolService {
	public List<Rol> findAll();
	public void save(Rol rol);
	public Optional<Rol> findOne(Long id);
	public void delete(Long id);
	public Optional<Rol>getByRolNombre(RolNombre rolNombre);
	public boolean exitsById(Long id);

}
