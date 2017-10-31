package com.tikal.aeronautica.model.otBody;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.tikal.aeronautica.model.Empleado;

public class Discrepancia {
	private Taller taller;
	private Seccion seccion;
	private String descripcion;
	private String accion;
	private int folio;
	private Calendar fechaApertura;
	private List<Componente> losComponentes;
	private Empleado removidoPor;
	private Empleado instaladoPor;
	private Empleado originadoPor;
	
	
	public Discrepancia() {
		super();
		this.losComponentes = new LinkedList<Componente>();
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
	 * Recupera el valor del campo seccion para su uso.
	 * @return the seccion
	 */
	public Seccion getSeccion() {
		return seccion;
	}
	/**
	 * Establece el valor del campo seccion.
	 * @param seccion the seccion to set
	 */
	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}
	/**
	 * Recupera el valor del campo taller para su uso.
	 * @return the taller
	 */
	public Taller getTaller() {
		return taller;
	}
	/**
	 * Establece el valor del campo taller.
	 * @param taller the taller to set
	 */
	public void setTaller(Taller taller) {
		this.taller = taller;
	}
	/**
	 * Recupera el valor del campo descripcion para su uso.
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * Establece el valor del campo descripcion.
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * Recupera el valor del campo accion para su uso.
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}
	/**
	 * Establece el valor del campo accion.
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
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
	 * Agrega un valor a la lista de componentes a partir del indice.
	 * @return the losComponentes
	 */
	public Componente getComponente(int index) {
		return losComponentes.get(index);
	}
	/**
	 * Agrega un valor a la lista de componentes.
	 * @param losComponentes the losComponentes to set
	 */
	public void setComponente(Componente componente) {
		this.losComponentes.add(componente);
	}
	/**
	 * Recupera el valor del campo removidoPor para su uso.
	 * @return the removidoPor
	 */
	public Empleado getRemovidoPor() {
		return removidoPor;
	}
	/**
	 * Establece el valor del campo removidoPor.
	 * @param removidoPor the removidoPor to set
	 */
	public void setRemovidoPor(Empleado removidoPor) {
		this.removidoPor = removidoPor;
	}
	/**
	 * Recupera el valor del campo instaladoPor para su uso.
	 * @return the instaladoPor
	 */
	public Empleado getInstaladoPor() {
		return instaladoPor;
	}
	/**
	 * Establece el valor del campo instaladoPor.
	 * @param instaladoPor the instaladoPor to set
	 */
	public void setInstaladoPor(Empleado instaladoPor) {
		this.instaladoPor = instaladoPor;
	}
	/**
	 * Recupera el valor del campo originadoPor para su uso.
	 * @return the originadoPor
	 */
	public Empleado getOriginadoPor() {
		return originadoPor;
	}
	/**
	 * Establece el valor del campo originadoPor.
	 * @param originadoPor the originadoPor to set
	 */
	public void setOriginadoPor(Empleado originadoPor) {
		this.originadoPor = originadoPor;
	}
	

}
