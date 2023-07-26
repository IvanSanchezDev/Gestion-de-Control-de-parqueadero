package com.proyecto.parqueadero.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.parqueadero.dao.ITarifaDao;
import com.proyecto.parqueadero.interfaces.IVehiculos;
import com.proyecto.parqueadero.interfacesService.IVehiculosService;
import com.proyecto.parqueadero.modelo.Vehiculos;
import com.proyecto.parqueadero.reportes.FacturaPdfView;

@Service
public class vehiculosService implements IVehiculosService {

	@Autowired
	private IVehiculos data;

	@Autowired
	private ITarifaDao dataTarifa;

	@Override
	public List<Vehiculos> listar() {
		List<Vehiculos> listaVehiculos = data.listaVehiculos();
		// Collections.sort((List<Vehiculos>) listaVehiculos);
		return listaVehiculos;

	}

	@Override
	public Optional<Vehiculos> listarId(int id) {
		return data.findById(id);
	}

	@Override
	public void save(Vehiculos v) {

		Calendar cal = Calendar.getInstance();

		Optional<Vehiculos> vehiculoDisponible = data.traerVehiculo(v.getPlaca());// ESTADO DISPONBLE

		try {

			if (vehiculoDisponible.isEmpty()) {
				
				Vehiculos vehiculo = new Vehiculos();
				Date date = cal.getTime();
				v.setHora_entrada(date);
				v.setPlaca(v.getPlaca().toUpperCase());

				v.setEstado("Disponible");

				vehiculo = data.save(v);

				try {

					DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
					String placa = v.getPlaca();
					String tipo = v.getTipo_vehiculo();
					String fecha = dateFormat.format(v.getHora_entrada());

					FacturaPdfView generator = new FacturaPdfView();
					generator.generateFactura(placa, tipo, fecha);

				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
				}

			} else {

				

				try {

					Date dateSalida = cal.getTime();

					Vehiculos infoVehiculo = vehiculoDisponible.get();

					int tiempo = 0;
					int horasACobrar = 0;
					int valorApagar = 0;

					tiempo = (int) (dateSalida.getTime() - infoVehiculo.getHora_entrada().getTime());
					horasACobrar = (int) TimeUnit.HOURS.convert(tiempo, TimeUnit.MILLISECONDS);
					horasACobrar++;
					if (infoVehiculo.getTipo_vehiculo().equals("carro")) {
						valorApagar = horasACobrar * dataTarifa.tarifaCarro();
					} else if (infoVehiculo.getTipo_vehiculo().equals("moto")) {
						valorApagar = horasACobrar * dataTarifa.tarifaMoto();
					}

					data.updateSalida(dateSalida, valorApagar, v.getPlaca());

				} catch (Exception e) {
					System.out.print("ERRORS: " + e.getMessage());
				}

			}

		} catch (Exception e) {
			System.out.print("ERRORS: " + e.getMessage());
		}

	}

	@Override
	public void delete(int id) {
		data.deleteById(id);

	}

	@Override
	public double cerrarCaja() {
		double valor = data.ingresos();
		return valor;
	}

	@Override
	public Vehiculos traerUltimoVehiculo() {
		return data.ultimoVehiculo();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Vehiculos> listar(Pageable pageable) {

		return data.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Vehiculos> findByPlacaStartsWith(String placa, Pageable pageable) {
		if (placa != null) {
			return data.findByPlacaStartsWith(placa, pageable);
		} else {
			return data.findAll(pageable);
		}

	}

	@Override
	public Page<Vehiculos> findByFecha(String fecha, Pageable pageable) {
		if (fecha != null) {
			return data.findByFecha(fecha, pageable);
		} else {
			return data.findAll(pageable);
		}
	}

	@Override
	public Double ingresosCarro() {

		Optional<Double> informacionIngresos = data.ingresosCarros();
		double ingresosCarro;
		if (informacionIngresos.isEmpty()) {
			return ingresosCarro = 0.0;
		} else {
			ingresosCarro = data.ingresosCarros().get();
		}
		return ingresosCarro;
	}

	@Override
	public Double ingresosMoto() {
		Optional<Double> informacionIngresosMoto = data.ingresosMotos();
		double ingresosMoto;
		if (informacionIngresosMoto.isEmpty()) {
			return ingresosMoto = 0.0;
		} else {
			ingresosMoto = data.ingresosMotos().get();
		}
		return ingresosMoto;
	}

	@Override
	public Integer cantidadCarros() {
		Optional<Integer>informacionCantidadCarros=data.cantidadCarros();
		
		Integer cantidadCarros;
		if(informacionCantidadCarros.isEmpty()) {
			return cantidadCarros=0;
		}else {
			cantidadCarros= data.cantidadCarros().get();
		}
		return cantidadCarros;
	}

	@Override
	public Integer cantidadMotos() {
		Optional<Integer>informacionCantidadMotos=data.cantidadMotos();
		Integer cantidadMotos;
		if(informacionCantidadMotos.isEmpty()) {
			return cantidadMotos=0;
		}else {
			cantidadMotos= data.cantidadMotos().get();
		}
		return cantidadMotos;
	}

	@Override
	public Integer vehiculosActivos() {
		Optional<Integer>infoVehiculosActivos=data.vehiculosActivos();
		Integer cantVehiculosActivos;
		if(infoVehiculosActivos.isEmpty()) {
			return cantVehiculosActivos=0;
		}else {
			cantVehiculosActivos=data.vehiculosActivos().get();
		}
		
		return cantVehiculosActivos;
	}

}
