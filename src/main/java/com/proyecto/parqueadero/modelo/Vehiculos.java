package com.proyecto.parqueadero.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "vehiculo")
public class Vehiculos implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_vehiculo;

	@NotEmpty
	@Size(min=1, max=6)
	private String placa;

	private String tipo_vehiculo;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date hora_entrada;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date hora_salida;

	private double valor_pagado;

	private String estado;

	

	public Long getId_vehiculo() {
		return id_vehiculo;
	}

	public void setId_vehiculo(Long id_vehiculo) {
		this.id_vehiculo = id_vehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipo_vehiculo() {
		return tipo_vehiculo;
	}

	public void setTipo_vehiculo(String tipo_vehiculo) {
		this.tipo_vehiculo = tipo_vehiculo;
	}

	public Date getHora_entrada() {
		return hora_entrada;
	}

	public void setHora_entrada(Date hora_entrada) {
		this.hora_entrada = hora_entrada;
	}

	public Date getHora_salida() {
		return hora_salida;
	}

	public void setHora_salida(Date hora_salida) {
		this.hora_salida = hora_salida;
	}

	public double getValor_pagado() {
		return valor_pagado;
	}

	public void setValor_pagado(double valor_pagado) {
		this.valor_pagado = valor_pagado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Vehiculos [id_vehiculo=" + id_vehiculo + ", placa=" + placa + ", tipo_vehiculo=" + tipo_vehiculo
				+ ", hora_entrada=" + hora_entrada + ", hora_salida=" + hora_salida + ", valor_pagado=" + valor_pagado
				+ ", estado=" + estado + "]";
	}

}
