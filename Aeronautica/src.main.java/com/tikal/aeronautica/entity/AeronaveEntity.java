package com.tikal.aeronautica.entity;

import  com.googlecode.objectify.annotation.Entity ;
import  com.googlecode.objectify.annotation.Id ;
import  com.googlecode.objectify.annotation.Index ;

//private String matricula;
//private String modelo;
//private String numeroSerie;
//private int tiempoVuelo;
//private int aterrizajes;


@Entity 
public  class  Aeronave{
    @Id  Long Matricula;
    @Index  String modelo;
    int r;
}


