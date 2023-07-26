package com.proyecto.parqueadero.interfacesService;

import java.util.List;
import java.util.Optional;

import com.proyecto.parqueadero.modelo.Usuario;

public interface IUsuarioService {
	public List<Usuario> findAll();
	public void save(Usuario usuario);
	public Optional<Usuario> findOne(Long id);
	public void delete(Long id);
	public Optional<Usuario>getByUsername(String username);
	public boolean exitsById(Long id);
}
