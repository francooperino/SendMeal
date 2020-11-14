package com.example.lab1appmoviles.room;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.lab1appmoviles.Pedido;
import com.example.lab1appmoviles.Plato;

import java.util.ArrayList;
import java.util.List;

public class PedidoConPlatos {

        @Embedded public Pedido pedido;
        @Relation(
                parentColumn = "idPedido",
                entityColumn = "id",
                entity = Plato.class

        )
        public List<Plato> platos;
        public  PedidoConPlatos(Pedido pedido,List<Plato> platos){
                this.pedido = pedido;
                this.platos = platos;
        }
}
