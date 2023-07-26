package com.proyecto.parqueadero.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tarifas")
public class Tarifa implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String tipo_vehiculo;

	@NotNull
	private int valor_hora;

	

	@NotNull
	private int valor_mes;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo_vehiculo() {
		return tipo_vehiculo;
	}

	public void setTipo_vehiculo(String tipo_vehiculo) {
		this.tipo_vehiculo = tipo_vehiculo;
	}

	public int getValor_hora() {
		return valor_hora;
	}

	public void setValor_hora(int valor_hora) {
		this.valor_hora = valor_hora;
	}

	

	public int getValor_mes() {
		return valor_mes;
	}

	public void setValor_mes(int valor_mes) {
		this.valor_mes = valor_mes;
	}

	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

}
