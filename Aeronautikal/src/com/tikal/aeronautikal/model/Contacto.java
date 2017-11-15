package com.tikal.aeronautikal.model;

public class Contacto {
	private String nombre;
	private String telefono;
	private String correoElectronico;
	/**
	 * Recupera el valor del campo nombre para su uso.
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Establece el valor del campo nombre.
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Recupera el valor del campo telefono para su uso.
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * Establece el valor del campo telefono.
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * Recupera el valor del campo correoElectronico para su uso.
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	/**
	 * Establece el valor del campo correoElectronico.
	 * @param correoElectronico the correoElectronico to set
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	

}
