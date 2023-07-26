package com.proyecto.parqueadero.service;


import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.parqueadero.interfaces.IUsuario;
import com.proyecto.parqueadero.interfacesService.IUsuarioService;
import com.proyecto.parqueadero.modelo.Usuario;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private IUsuario usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}

	@Override
	public void save(Usuario usuario) {
		//try {
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			usuarioRepository.save(usuario);
		//}catch(Exception e) {
		//	System.out.println("ERRORR" + e.getMessage());
		//}
		
		
			
		
	}

	@Override
	public Optional<Usuario> findOne(Long id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		usuarioRepository.deleteById(id);

	}

	@Override
	public Optional<Usuario> getByUsername(String username) {
		// TODO Auto-generated method stub
		return usuarioRepository.findByUsername(username);
	}

	@Override
	public boolean exitsById(Long id) {
		// TODO Auto-generated method stub
		return usuarioRepository.existsById(id);
	}

}
