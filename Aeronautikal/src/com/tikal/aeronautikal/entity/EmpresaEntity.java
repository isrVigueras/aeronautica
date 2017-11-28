package com.tikal.aeronautikal.entity;

import com.googlecode.objectify.annotation.Container;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.tikal.aeronautikal.model.DatosFacturacion;
import com.tikal.aeronautikal.model.otBody.Seccion;
import com.tikal.aeronautikal.model.otBody.Taller;


@Entity

public class EmpresaEntity implements BaseEntity{

	
	@Id private long id;	
	private String nickName;
	private DatosFacturacion datosFacturacion;
	@Container OrdenEntity orden;
	

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public DatosFacturacion getDatosFacturacion() {
		return datosFacturacion;
	}
	public void setDatosFacturacion(DatosFacturacion datosFacturacion) {
		this.datosFacturacion = datosFacturacion;
	}
	
	

}
