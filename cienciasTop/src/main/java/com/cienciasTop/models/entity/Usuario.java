package com.cienciasTop.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "numeroCuenta")
	private String numeroCuenta;
	@Column(name = "numeroCelular")
	private String numeroCelular;
	@Column(name = "correoElectronico")
	private String correoElectronico;
	@Column(name = "carrera")
	private String carrera;
	@Column(name = "pumaPuntos")
	private Integer pumaPuntos;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public Integer getPumaPuntos() {
		return pumaPuntos;
	}

	public void setPumaPuntos(Integer pumaPuntos) {
		this.pumaPuntos = pumaPuntos;
	}
	
	private static final long serialVersionUID = 1L;

}
