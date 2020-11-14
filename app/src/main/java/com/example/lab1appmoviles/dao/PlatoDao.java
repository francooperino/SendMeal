package com.example.lab1appmoviles.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lab1appmoviles.Plato;

import java.util.List;

@Dao
public interface PlatoDao {
    @Insert
    void insertar(Plato plato);

    @Delete
    void borrar(Plato plato);

    @Update
    void actualizar(Plato plato);

    @Query("SELECT * FROM plato WHERE id = :id LIMIT 1")
    Plato buscar(String id);

    @Query("SELECT p.titulo,p.descripcion,p.calorias,p.precio,p.id,p.pedidoCreatorId,p.id_pedido  FROM plato p GROUP BY p.titulo")
    List<Plato> buscarTodos();


    @Query("DELETE FROM plato")
    void borrarPlatos();

}