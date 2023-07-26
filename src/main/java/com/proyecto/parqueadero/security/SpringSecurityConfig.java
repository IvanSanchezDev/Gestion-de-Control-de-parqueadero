package com.proyecto.parqueadero.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import com.proyecto.parqueadero.auth.handlers.LoginSuccesHandler;
import com.proyecto.parqueadero.service.JpaUserDetailsService;

@Configuration
public class SpringSecurityConfig {

	@Autowired
	private LoginSuccesHandler succesHandler;

	@Autowired
	private JpaUserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/", "/styles/**", "/js/**", "/img/**", "/login").permitAll()	
				.antMatchers("/listarVehiculos/**").hasAnyRole("USER").antMatchers("/newvehiculos/**")
				.hasAnyRole("USER").antMatchers("/admin/estadisticas/**").hasAnyRole("ADMIN")
				//.antMatchers("/admin/historialAdmin/**").hasAnyRole("ADMIN")
				.antMatchers("/admin/historialAdminFechas/**").hasAnyRole("ADMIN")
				.anyRequest().authenticated().and()
				.formLogin().successHandler(succesHandler).loginPage("/login").defaultSuccessUrl("/newVehiculo")
				.permitAll().and().logout().permitAll().and().exceptionHandling().accessDeniedPage("/error_403");

		return http.build();
	}

}
