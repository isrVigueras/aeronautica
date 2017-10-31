package com.tikal.aeronautica.model.costos;

import java.util.LinkedList;
import java.util.List;

import com.tikal.aeronautica.model.Contacto;

public class Proveedor {
	private String nombre;
	private String direccion;
	private List<Contacto> losContactos;
	

	public Proveedor() {
		this.losContactos = new LinkedList<Contacto>();
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
	 * Recupera el valor del campo direccion para su uso.
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}


	/**
	 * Establece el valor del campo direccion.
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	/**
	 * Recupera el valor del campo losContactos para su uso.
	 * @return the losContactos
	 */
	public List<Contacto> getLosContactos() {
		return losContactos;
	}


	/**
	 * Establece el valor del campo losContactos.
	 * @param losContactos the losContactos to set
	 */
	public void setLosContactos(List<Contacto> losContactos) {
		this.losContactos = losContactos;
	}

}
