/**
 * Clase pojo para representar una orden de trabajo con sus datos escenciales.
 */
package com.tikal.aeronautica.model;

public class OrdenDeTrabajo {
	private Empresa laEmpresa;
	private Aeronave elAeronave;

	/**
	 * Recupera el valor del campo laEmpresa para su uso.
	 * @return the laEmpresa
	 */
	public Empresa getLaEmpresa() {
		return laEmpresa;
	}

	/**
	 * Establece el valor del campo laEmpresa.
	 * @param laEmpresa the laEmpresa to set
	 */
	public void setLaEmpresa(Empresa laEmpresa) {
		this.laEmpresa = laEmpresa;
	}

	/**
	 * Recupera el valor del campo elAeronave para su uso.
	 * @return the elAeronave
	 */
	public Aeronave getElAeronave() {
		return elAeronave;
	}

	/**
	 * Establece el valor del campo elAeronave.
	 * @param elAeronave the elAeronave to set
	 */
	public void setElAeronave(Aeronave elAeronave) {
		this.elAeronave = elAeronave;
	}
	
	

}
