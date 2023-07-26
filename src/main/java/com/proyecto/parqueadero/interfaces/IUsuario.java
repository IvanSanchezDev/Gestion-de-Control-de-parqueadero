package com.proyecto.parqueadero.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.proyecto.parqueadero.modelo.Usuario;

@Repository
public interface IUsuario extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario>findByUsername(String username);
	
}
