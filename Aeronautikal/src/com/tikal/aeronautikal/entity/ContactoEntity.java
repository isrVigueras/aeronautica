package com.tikal.aeronautikal.entity;

import com.googlecode.objectify.annotation.Container;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


@Entity
public class ContactoEntity{
	@Id private Long id;
	@Index private String nombre;
	@Index private String telefono;
	 private String correoElectronico;

	@Container EmpresaEntity empresa;
	//@Container OrdenEntity orden;
	
	public EmpresaEntity getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaEntity empresa) {
		this.empresa = empresa;
	}
	
	//public OrdenEntity getOrden() {
	//	return orden;
	//}
	//public void setOrden(OrdenEntity orden) {
	//	this.orden = orden;
	//}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
 
	

}
