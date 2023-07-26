package com.proyecto.parqueadero.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.parqueadero.auth.enums.RolNombre;
import com.proyecto.parqueadero.modelo.Rol;

@Repository
public interface IRol  extends JpaRepository<Rol, Long>{
	Optional<Rol> findByRolNombre(RolNombre rolNombre);
	
}
