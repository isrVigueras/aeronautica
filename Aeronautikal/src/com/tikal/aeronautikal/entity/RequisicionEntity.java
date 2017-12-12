package com.tikal.aeronautikal.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
@Entity
public class RequisicionEntity {
		
		@Id Long folio;
		@Index Long idComponente;
		Long idDiscrepancia;
		Integer cantidad;
		String d_componente;
		String fechaApertura;
		@Index String estatus;
		
		
		
		
		public Long getIdDiscrepancia() {
			return idDiscrepancia;
		}
		public void setIdDiscrepancia(Long idDiscrepancia) {
			this.idDiscrepancia = idDiscrepancia;
		}
		public String getEstatus() {
			return estatus;
		}
		public void setEstatus(String estatus) {
			this.estatus = estatus;
		}
		public Long getFolio() {
			return folio;
		}
		public void setFolio(Long folio) {
			this.folio = folio;
		}
		public Long getIdComponente() {
			return idComponente;
		}
		public void setIdComponente(Long idComponente) {
			this.idComponente = idComponente;
		}
		public Integer getCantidad() {
			return cantidad;
		}
		public void setCantidad(Integer cantidad) {
			this.cantidad = cantidad;
		}
		public String getD_componente() {
			return d_componente;
		}
		public void setD_componente(String d_componente) {
			this.d_componente = d_componente;
		}
		public String getFechaApertura() {
			return fechaApertura;
		}
		public void setFechaApertura(String fechaApertura) {
			this.fechaApertura = fechaApertura;
		}
		
		
		
		

}
