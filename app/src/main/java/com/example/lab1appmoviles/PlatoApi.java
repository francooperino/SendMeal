package com.example.lab1appmoviles;


import java.util.UUID;

public class PlatoApi {


    private UUID id;
    private String titulo;
    private String descripcion;
    private Integer calorias;
    private Double precio;
    private long pedidoCreatorId;

    private long id_pedido;

    public long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public PlatoApi(String titulo, String descripcion, Integer calorias, Double precio, UUID id) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.calorias = calorias;
        this.precio = precio;
        this.id = id;
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

    public long getPedidoCreatorId() {
        return pedidoCreatorId;
    }

    public void setPedidoCreatorId(long pedidoCreatorId) {
        this.pedidoCreatorId = pedidoCreatorId;
    }

    public UUID getId() {
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

    public void setId(UUID id) {
        this.id = id;
    }
}
