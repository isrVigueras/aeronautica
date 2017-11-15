package com.tikal.aeronautikal.model.otBody;

public class Componente {
	private String nombre;
	private String numeroParte;
	private ComponenteStatus status;
	private int cantidadPedida;
	private int cantidadEntregada;
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
	 * Recupera el valor del campo numeroParte para su uso.
	 * @return the numeroParte
	 */
	public String getNumeroParte() {
		return numeroParte;
	}
	/**
	 * Establece el valor del campo numeroParte.
	 * @param numeroParte the numeroParte to set
	 */
	public void setNumeroParte(String numeroParte) {
		this.numeroParte = numeroParte;
	}
	/**
	 * Recupera el valor del campo status para su uso.
	 * @return the status
	 */
	public ComponenteStatus getStatus() {
		return status;
	}
	/**
	 * Establece el valor del campo status.
	 * @param status the status to set
	 */
	public void setStatus(ComponenteStatus status) {
		this.status = status;
	}
	/**
	 * Recupera el valor del campo cantidadPedida para su uso.
	 * @return the cantidadPedida
	 */
	public int getCantidadPedida() {
		return cantidadPedida;
	}
	/**
	 * Establece el valor del campo cantidadPedida.
	 * @param cantidadPedida the cantidadPedida to set
	 */
	public void setCantidadPedida(int cantidadPedida) {
		this.cantidadPedida = cantidadPedida;
	}
	/**
	 * Recupera el valor del campo cantidadEntregada para su uso.
	 * @return the cantidadEntregada
	 */
	public int getCantidadEntregada() {
		return cantidadEntregada;
	}
	/**
	 * Establece el valor del campo cantidadEntregada.
	 * @param cantidadEntregada the cantidadEntregada to set
	 */
	public void setCantidadEntregada(int cantidadEntregada) {
		this.cantidadEntregada = cantidadEntregada;
	}
	

}
