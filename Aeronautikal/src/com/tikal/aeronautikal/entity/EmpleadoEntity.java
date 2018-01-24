package com.tikal.aeronautikal.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class EmpleadoEntity{
	@Id Long id;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombre;
	private String nombreCompleto;
	@Index private Long idPuesto;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	public Long getIdPuesto() {
		return idPuesto;
	}
	public void setIdPuesto(Long idPuesto) {
		this.idPuesto = idPuesto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getNombreCompleto() {
		nombreCompleto=this.nombre+" "+this.apellidoPaterno+" "+this.apellidoMaterno;
		return nombreCompleto;
	}
	
//	public NombreCompleto(String nombre, String apellidoPaterno, String apellidoMaterno) {
//		this.nombre = nombre;
//		this.apellidoPaterno = apellidoPaterno;
//		this.apellidoMaterno = apellidoMaterno;
//	}
	
} 
