package com.tikal.aeronautikal.model.costos;

public class ComponenteCosto extends com.tikal.aeronautikal.model.otBody.Componente {

	private float costoCompra;
	private float costoVenta;
	private Proveedor elProveedor;
	public ComponenteCosto() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Recupera el valor del campo costoCompra para su uso.
	 * @return the costoCompra
	 */
	public float getCostoCompra() {
		return costoCompra;
	}
	/**
	 * Establece el valor del campo costoCompra.
	 * @param costoCompra the costoCompra to set
	 */
	public void setCostoCompra(float costoCompra) {
		this.costoCompra = costoCompra;
	}
	/**
	 * Recupera el valor del campo costoVenta para su uso.
	 * @return the costoVenta
	 */
	public float getCostoVenta() {
		return costoVenta;
	}
	/**
	 * Establece el valor del campo costoVenta.
	 * @param costoVenta the costoVenta to set
	 */
	public void setCostoVenta(float costoVenta) {
		this.costoVenta = costoVenta;
	}
	/**
	 * Recupera el valor del campo elProveedor para su uso.
	 * @return the elProveedor
	 */
	public Proveedor getElProveedor() {
		return elProveedor;
	}
	/**
	 * Establece el valor del campo elProveedor.
	 * @param elProveedor the elProveedor to set
	 */
	public void setElProveedor(Proveedor elProveedor) {
		this.elProveedor = elProveedor;
	}

}
