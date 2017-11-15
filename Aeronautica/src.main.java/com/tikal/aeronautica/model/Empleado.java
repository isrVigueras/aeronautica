package com.tikal.aeronautica.model;

public class Empleado {
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombre;
	private String puesto;
	/**
	 * Recupera el valor del campo apellidoPaterno para su uso.
	 * @return the apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	/**
	 * Establece el valor del campo apellidoPaterno.
	 * @param apellidoPaterno the apellidoPaterno to set
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	/**
	 * Recupera el valor del campo apellidoMaterno para su uso.
	 * @return the apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	/**
	 * Establece el valor del campo apellidoMaterno.
	 * @param apellidoMaterno the apellidoMaterno to set
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
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
	 * Recupera el valor del campo puesto para su uso.
	 * @return the puesto
	 */
	public String getPuesto() {
		return puesto;
	}
	/**
	 * Establece el valor del campo puesto.
	 * @param puesto the puesto to set
	 */
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

}
