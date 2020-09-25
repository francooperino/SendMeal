package com.example.lab1appmoviles;

public class Plato {
    private String titulo;
    private String descripcion;
    private Integer calorias;
    private Double Precio;

    public Plato(String titulo, String descripcion, Integer calorias, Double precio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.calorias = calorias;
        Precio = precio;
    }
//getters
    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public Double getPrecio() {
        return Precio;
    }
//setters


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public void setPrecio(Double precio) {
        Precio = precio;
    }
}
