package com.example.lab1appmoviles.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.lab1appmoviles.Pedido;
import com.example.lab1appmoviles.Plato;
import com.example.lab1appmoviles.room.PedidoConPlatos;

import java.util.List;

@Dao
public interface PedidoDao {

    @Transaction
    @Insert
    long insertPedido(Pedido pedido);
    @Insert
    void insertPlatos(List<Plato> platos);

    @Delete
    void borrar(Pedido pedido);

    @Update
    void actualizar(Pedido pedido);

    @Query("SELECT * FROM pedido WHERE idPedido = :id LIMIT 1")
    Pedido buscar(String id);

    @Query("SELECT * FROM pedido")
    List<Pedido> buscarTodos();

    @Query("DELETE FROM pedido")
    void borrarPedidos();

    @Transaction
    @Query("SELECT * FROM pedido")
    public List<PedidoConPlatos> getPedidossWithPlatos();



}
