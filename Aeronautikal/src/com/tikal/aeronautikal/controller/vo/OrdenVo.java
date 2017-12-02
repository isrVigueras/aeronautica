package com.tikal.aeronautikal.controller.vo;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.tikal.aeronautikal.entity.BaseEntity;
import com.tikal.aeronautikal.model.Empresa;



@Entity
public class OrdenVo {
	
	@Id Long folio;
	String fechaApertura;
	String con_nombre;
	Long con_telefono;
	String con_correo;
	String empresa;
	String a_matricula;
	String a_modelo;
	String n_serie;
	Integer a_t_vuelo;
	String a_t_aterrizaje;
	
	@Index @Load Ref<Empresa> refEmpresa;
	
	public String getA_modelo() {
		return a_modelo;
	}
	public void setA_modelo(String a_modelo) {
		this.a_modelo = a_modelo;
	}
	public Ref<Empresa> getRefEmpresa() {
		return refEmpresa;
	}
	public void setRefEmpresa(Ref<Empresa> refEmpresa) {
		this.refEmpresa = refEmpresa;
	}
	public Long getFolio() {
		return folio;
	}
	public void setFolio(Long folio) {
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
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getA_matricula() {
		return a_matricula;
	}
	public void setA_matricula(String a_matricula) {
		this.a_matricula = a_matricula;
	}
	public String getModelo() {
		return a_modelo;
	}
	public void setModelo(String a_modelo) {
		this.a_modelo = a_modelo;
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
