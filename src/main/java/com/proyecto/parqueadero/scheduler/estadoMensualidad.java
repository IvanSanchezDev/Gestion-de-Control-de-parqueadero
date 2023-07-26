package com.proyecto.parqueadero.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.proyecto.parqueadero.interfaces.IMensualidades;

@Component
public class estadoMensualidad {
	
	@Autowired
	private IMensualidades data;

	
	@Scheduled(cron = "0 5 7 * * *")
    public void cambiarEstadoHabilitado() {
		System.out.println("Se ejecuto la tarea diaria");
        data.actualizarMensualidad();
    }
}
