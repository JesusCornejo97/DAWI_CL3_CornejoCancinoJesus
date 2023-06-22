package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class ProductoReport {

	private List<Producto> productosList;
	private String anio;
	private String descripcion;
	private String nombre;
	
	public ProductoReport() {
		super();
		this.productosList = new ArrayList<>();
	}

	

	public List<Producto> getProductosList() {
		return productosList;
	}



	public void setProductosList(List<Producto> productosList) {
		this.productosList = productosList;
	}



	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
