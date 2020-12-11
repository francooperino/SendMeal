package com.example.lab1appmoviles;


import java.util.UUID;

public class PedidoApi {

    private UUID idPedido;
    private String email;
    private String direccion;
    private String tipoPedido;
    private Double precio;

    public PedidoApi(UUID idPedido, String email, String direccion, String tipoPedido, Double precio) {
        this.idPedido = idPedido;
        this.email = email;
        this.direccion = direccion;
        this.tipoPedido = tipoPedido;
        this.precio = precio;
    }

    public UUID getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(UUID idPedido) {
        this.idPedido = idPedido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
