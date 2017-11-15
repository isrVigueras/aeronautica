/**
 * Clase pojo para representar una orden de trabajo con sus datos escenciales.
 */
package com.tikal.aeronautikal.model;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.tikal.aeronautikal.model.otBody.Discrepancia;


public class OrdenDeTrabajo {
	private Empresa laEmpresa;
	private Aeronave elAeronave;
	private Contacto datosContacto;
	private int folio;
	private Calendar fechaApertura;
	private String condiciones;
	private List<Discrepancia> discrepancias;

	
	
	public OrdenDeTrabajo() {
		super();
		this.discrepancias = new LinkedList<Discrepancia>();
	}

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

	/**
	 * Recupera el valor del campo datosContacto para su uso.
	 * @return the datosContacto
	 */
	public Contacto getDatosContacto() {
		return datosContacto;
	}

	/**
	 * Establece el valor del campo datosContacto.
	 * @param datosContacto the datosContacto to set
	 */
	public void setDatosContacto(Contacto datosContacto) {
		this.datosContacto = datosContacto;
	}

	/**
	 * Recupera el valor del campo folio para su uso.
	 * @return the folio
	 */
	public int getFolio() {
		return folio;
	}

	/**
	 * Establece el valor del campo folio.
	 * @param folio the folio to set
	 */
	public void setFolio(int folio) {
		this.folio = folio;
	}

	/**
	 * Recupera el valor del campo fechaApertura para su uso.
	 * @return the fechaApertura
	 */
	public Calendar getFechaApertura() {
		return fechaApertura;
	}

	/**
	 * Establece el valor del campo fechaApertura.
	 * @param fechaApertura the fechaApertura to set
	 */
	public void setFechaApertura(Calendar fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	/**
	 * Recupera el valor del campo condiciones para su uso.
	 * @return the condiciones
	 */
	public String getCondiciones() {
		return condiciones;
	}

	/**
	 * Establece el valor del campo condiciones.
	 * @param condiciones the condiciones to set
	 */
	public void setCondiciones(String condiciones) {
		this.condiciones = condiciones;
	}
	
	/**
	 * Inserta valores en la lista de discrepancia
	 */
	public void addDiscrepancia(Discrepancia nuevaDiscrepancia) {
		this.discrepancias.add(nuevaDiscrepancia);
	}

	/**
	 * Recupera a partir del indice en la lista una discrepancia.
	 */
	public Discrepancia getDiscrepanciaFromIndex(int i) {
		return this.discrepancias.get(i);
	}
}
