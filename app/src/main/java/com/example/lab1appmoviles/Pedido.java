package com.example.lab1appmoviles;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pedido {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String descripcion;
    private Double precio;

    public Pedido(Long id, String descripcion, Double precio) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
