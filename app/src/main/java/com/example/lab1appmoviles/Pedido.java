package com.example.lab1appmoviles;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pedido {
    @PrimaryKey(autoGenerate = true)
    private Long idPedido;
    private String email;
    private String direccion;
    private String tipoPedido;
    private Double precio;

    public Pedido(Long idPedido, String email, String direccion, String tipoPedido, Double precio) {
        this.idPedido = idPedido;
        this.email = email;
        this.direccion = direccion;
        this.tipoPedido = tipoPedido;
        this.precio = precio;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
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
