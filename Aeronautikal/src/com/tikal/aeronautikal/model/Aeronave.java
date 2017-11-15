  package com.tikal.aeronautikal.model;

public class Aeronave {
	private String matricula;
	private String modelo;
	private String numeroSerie;
	private int tiempoVuelo;
	private int aterrizajes;
	/**
	 * Recupera el valor del campo matricula para su uso.
	 * @return the matricula
	 */
	public String getMatricula() {
		return matricula;
	}
	/**
	 * Establece el valor del campo matricula.
	 * @param matricula the matricula to set
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	/**
	 * Recupera el valor del campo modelo para su uso.
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}
	/**
	 * Establece el valor del campo modelo.
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	/**
	 * Recupera el valor del campo numeroSerie para su uso.
	 * @return the numeroSerie
	 */
	public String getNumeroSerie() {
		return numeroSerie;
	}
	/**
	 * Establece el valor del campo numeroSerie.
	 * @param numeroSerie the numeroSerie to set
	 */
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	/**
	 * Recupera el valor del campo tiempoVuelo para su uso.
	 * @return the tiempoVuelo
	 */
	public int getTiempoVuelo() {
		return tiempoVuelo;
	}
	/**
	 * Establece el valor del campo tiempoVuelo.
	 * @param tiempoVuelo the tiempoVuelo to set
	 */
	public void setTiempoVuelo(int tiempoVuelo) {
		this.tiempoVuelo = tiempoVuelo;
	}
	/**
	 * Recupera el valor del campo aterrizajes para su uso.
	 * @return the aterrizajes
	 */
	public int getAterrizajes() {
		return aterrizajes;
	}
	/**
	 * Establece el valor del campo aterrizajes.
	 * @param aterrizajes the aterrizajes to set
	 */
	public void setAterrizajes(int aterrizajes) {
		this.aterrizajes = aterrizajes;
	}
	

}
