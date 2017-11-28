package com.tikal.aeronautikal.controller.vo;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.tikal.aeronautikal.entity.BaseEntity;



@Entity
public class OrdenVo implements BaseEntity {
	
	@Id Integer folio;
	String fechaApertura;
	String con_nombre;
	Long con_telefono;
	String con_correo;
	Integer empresa;
	String a_a_matricula;
	String modelo;
	String n_serie;
	Integer a_t_vuelo;
	String a_t_aterrizaje;
	public Integer getFolio() {
		return folio;
	}
	public void setFolio(Integer folio) {
		this.folio = folio;
	}
	public String getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	public String getCon_nombre() {
		return con_nombre;
	}
	public void setCon_nombre(String con_nombre) {
		this.con_nombre = con_nombre;
	}
	public Long getCon_telefono() {
		return con_telefono;
	}
	public void setCon_telefono(Long con_telefono) {
		this.con_telefono = con_telefono;
	}
	public String getCon_correo() {
		return con_correo;
	}
	public void setCon_correo(String con_correo) {
		this.con_correo = con_correo;
	}
	public Integer getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Integer empresa) {
		this.empresa = empresa;
	}
	public String getA_a_matricula() {
		return a_a_matricula;
	}
	public void setA_a_matricula(String a_a_matricula) {
		this.a_a_matricula = a_a_matricula;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getN_serie() {
		return n_serie;
	}
	public void setN_serie(String n_serie) {
		this.n_serie = n_serie;
	}
	public Integer getA_t_vuelo() {
		return a_t_vuelo;
	}
	public void setA_t_vuelo(Integer a_t_vuelo) {
		this.a_t_vuelo = a_t_vuelo;
	}
	public String getA_t_aterrizaje() {
		return a_t_aterrizaje;
	}
	public void setA_t_aterrizaje(String a_t_aterrizaje) {
		this.a_t_aterrizaje = a_t_aterrizaje;
	}
	
	
	
	
}
