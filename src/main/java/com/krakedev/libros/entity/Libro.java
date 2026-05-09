package com.krakedev.libros.entity;

public class Libro {

    private String codigo;
    private String titulo;
    private double precio;
    private String autor;

    // Constructor vacío
    public Libro() {

    }
    
    

    public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	// Get codigo
    public String getCodigo() {
        return codigo;
    }

    // Set codigo
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    // Get titulo
    public String getTitulo() {
        return titulo;
    }

    // Set titulo
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Get precio
    public double getPrecio() {
        return precio;
    }

    // Set precio
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
