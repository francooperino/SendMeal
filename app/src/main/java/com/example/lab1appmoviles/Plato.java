package com.example.lab1appmoviles;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;


@Entity

public class Plato {
/*
      Esta clase se utiliza para el guardado en la bd de room,
      Tenemos otra que se utiliza para traer los datos de la api
      PlatoApi, creamos dos clases, ya que el identificador de la api se genera como un uuid
      y no tenemos forma de usar la misma clase para room, ya que no conoce el tipo de dato uuid,
      y tampoco puede el id ser un string.
      Lo mismo pasa con pedido, por lo que creamos pedidoApi
 */
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String titulo;
    private String descripcion;
    private Integer calorias;
    private Double precio;
    private long pedidoCreatorId;
    @ForeignKey
            (entity = Pedido.class,
                    parentColumns = "idPedido",
                    childColumns = "id",
                    onDelete = CASCADE
            )
    private long id_pedido;

    public long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Plato(String titulo, String descripcion, Integer calorias, Double precio, Long id) {
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
