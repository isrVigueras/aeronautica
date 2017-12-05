package com.tikal.aeronautikal.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
@Entity
public class RequisicionEntity {
		
		@Id Long folio;
		Integer cantidad;
		String numero_parte;
		public Long getFolio() {
			return folio;
		}
		public void setFolio(Long folio) {
			this.folio = folio;
		}
		public Integer getCantidad() {
			return cantidad;
		}
		public void setCantidad(Integer cantidad) {
			this.cantidad = cantidad;
		}
		public String getNumero_parte() {
			return numero_parte;
		}
		public void setNumero_parte(String numero_parte) {
			this.numero_parte = numero_parte;
		}
		
		

}
