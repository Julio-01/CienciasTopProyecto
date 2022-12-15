package com.cienciasTop.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rentar_producto")
public class RentarProducto implements Serializable{

    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idUsuario;

    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProducto;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idRentado;

    @Column(name="fecha_inicio", nullable = false)
    String fecha_inicio;

    @Column(name="fecha_final", nullable = false)
    String fecha_fianl;


    public Long getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Long getIdRentado() {
        return this.idRentado;
    }

    public void setIdRentado(Long idRentado) {
        this.idRentado = idRentado;
    }

    public String getFecha_inicio() {
        return this.fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fianl() {
        return this.fecha_fianl;
    }

    public void setFecha_fianl(String fecha_fianl) {
        this.fecha_fianl = fecha_fianl;
    }

    @Override
    public String toString() {
        return "{" +
            " idUsuario='" + getIdUsuario() + "'" +
            ", idProducto='" + getIdProducto() + "'" +
            ", idRentado='" + getIdRentado() + "'" +
            ", fecha_inicio='" + getFecha_inicio() + "'" +
            ", fecha_fianl='" + getFecha_fianl() + "'" +
            "}";
    }

    private static final long serialVersionUID = 1L;

}