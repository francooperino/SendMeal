package com.example.lab1appmoviles;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity

public class Plato {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String titulo;
    private String descripcion;
    private Integer calorias;
    private Double precio;

    public Plato(String titulo, String descripcion, Integer calorias, Double precio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.calorias = calorias;
        this.precio = precio;
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
        return precio;
    }

    public Long getId() {
        return id;
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
        this.precio = precio;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
