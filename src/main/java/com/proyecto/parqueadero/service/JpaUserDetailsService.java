package com.proyecto.parqueadero.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.proyecto.parqueadero.modelo.Rol;

import com.proyecto.parqueadero.modelo.Usuario;


@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService{

	
	@Autowired
	private UsuarioService usuarioService;
	
	private Logger logger=LoggerFactory.getLogger(JpaUserDetailsService.class);
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario=usuarioService.getByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
		
		if(usuario==null) {
			logger.error("Error login: no existe el usuario '" + username + "'");
			throw new UsernameNotFoundException("username " + username + " no existe en el sistema");
		}
		
		List<GrantedAuthority> roles=new ArrayList<GrantedAuthority>();
		
		for(Rol role: usuario.getRoles()) {
			roles.add(new SimpleGrantedAuthority(role.getRolNombre().toString()));
		}
		
		
		if(roles.isEmpty()) {
			logger.error("Error login:  usuario '" + username + "' no tiene roles asignados");
			throw new UsernameNotFoundException("Error login:  usuario '" + username + "' no tiene roles asignados");
		}
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.isHabilitado(), true, true, true, roles );
	}
	
	

}
