package com.tikal.aeronautikal.model;

public class Empresa {
	private String nickName;
	private DatosFacturacion datosFacturacion;
	/**
	 * Recupera el valor del campo nickName para su uso.
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * Establece el valor del campo nickName.
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * Recupera el valor del campo datosFacturacion para su uso.
	 * @return the datosFacturacion
	 */
	public DatosFacturacion getDatosFacturacion() {
		return datosFacturacion;
	}
	/**
	 * Establece el valor del campo datosFacturacion.
	 * @param datosFacturacion the datosFacturacion to set
	 */
	public void setDatosFacturacion(DatosFacturacion datosFacturacion) {
		this.datosFacturacion = datosFacturacion;
	}
	
	

}
