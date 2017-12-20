package com.tikal.aeronautikal.controller.vo;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class DetalleOrdenVo {
	@Id private Long id;
	private Long idOrden;
	private String folioOrden;
	private String fecha;
	private String contacto;
	private String telefono;
	private String correo;
	private String nombreEmpresa;
	private String matricula;
	private String modelo;
	private String noSerie;
	private String tiempoVuelo;
	private String aterrizaje;
	
	
	
	public Long getIdOrden() {
		return idOrden;
	}
	public void setIdOrden(Long idOrden) {
		this.idOrden = idOrden;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFolioOrden() {
		return folioOrden;
	}
	public void setFolioOrden(String folioOrden) {
		this.folioOrden = folioOrden;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getNoSerie() {
		return noSerie;
	}
	public void setNoSerie(String noSerie) {
		this.noSerie = noSerie;
	}
	public String getTiempoVuelo() {
		return tiempoVuelo;
	}
	public void setTiempoVuelo(String tiempoVuelo) {
		this.tiempoVuelo = tiempoVuelo;
	}
	public String getAterrizaje() {
		return aterrizaje;
	}
	public void setAterrizaje(String aterrizaje) {
		this.aterrizaje = aterrizaje;
	}
	
	

}
