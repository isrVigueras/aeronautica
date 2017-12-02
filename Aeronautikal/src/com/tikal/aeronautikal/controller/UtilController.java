package com.tikal.aeronautikal.controller;

import java.util.ArrayList;
import java.util.List;

public class UtilController {

	
	private List<Long> crearListaDeIDsDeEmpleados(Long... ids) {
		List<Long> lista = new ArrayList<Long>();
		for(int i = 0; i < ids.length; i++) {
			lista.add(ids[i]);
		}
		return lista;
	}
}
