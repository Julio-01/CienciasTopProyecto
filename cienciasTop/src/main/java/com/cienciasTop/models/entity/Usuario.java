package com.cienciasTop.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;  
import java.util.ArrayList;
import java.util.HashMap;
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "numeroDeCuenta", length = 10, unique = true, nullable = false)
	private String numeroDeCuenta;
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@Column(name = "numeroDeCelular", nullable = false)
	private String numeroDeCelular;
//	@Pattern(regexp="^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+.unam.mx$")
	@Column(name = "correoElectronico", unique = true, nullable = false)
	private String correoElectronico;
	@Column(name = "carrera", nullable = false)
	private String carrera;
	@Column(name = "contrasena", length = 60, nullable = false)
	private String contrasena;
	@Min(0)
	@Max(500)
	@Column(name = "pumaPuntos", nullable = false)
	private Integer pumaPuntos;
	@Column(name = "enabled")
	private Boolean enabled;
	@Column(name = "productosRentadosTotales", nullable = false)
	private Integer productosRentadosTotales;
	// @Column(name = "productosRentados", nullable = false)
	// private HashMap<Integer,ArrayList<Productos> > productosRentados;



	public Integer getProductosRentadosTotales() {
		return this.productosRentadosTotales;
	}

	public void setProductosRentadosTotales(Integer productosRentadosTotales) {
		this.productosRentadosTotales = productosRentadosTotales;
	}

	// public HashMap<Integer,ArrayList<Productos>> getProductosRentados() {
	// 	return this.productosRentados;
	// }

	// public void setProductosRentados(HashMap<Integer,ArrayList<Productos>> productosRentados) {
	// 	this.productosRentados = productosRentados;
	// }

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="usuarios_roles", joinColumns= @JoinColumn(name="usuario_id"),
	inverseJoinColumns=@JoinColumn(name="role_id"),
	uniqueConstraints= {@UniqueConstraint(columnNames= {"usuario_id", "role_id"})})
	private List<Role> roles;
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}
	

	public String getNumeroDeCuenta() {
		return numeroDeCuenta;
	}



	public void setNumeroDeCuenta(String numeroDeCuenta) {
		this.numeroDeCuenta = numeroDeCuenta;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getNumeroDeCelular() {
		return numeroDeCelular;
	}



	public void setNumeroDeCelular(String numeroDeCelular) {
		this.numeroDeCelular = numeroDeCelular;
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



	public String getContrasena() {
		return contrasena;
	}



	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}



	public Integer getPumaPuntos() {
		return pumaPuntos;
	}



	public void setPumaPuntos(Integer pumaPuntos) {
		this.pumaPuntos = pumaPuntos;
	}



	public Boolean getEnabled() {
		return enabled;
	}



	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}



	public List<Role> getRoles() {
		return roles;
	}



	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}

