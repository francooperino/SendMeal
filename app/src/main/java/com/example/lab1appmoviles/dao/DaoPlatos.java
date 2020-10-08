package com.example.lab1appmoviles.dao;

import android.util.Log;

import com.example.lab1appmoviles.Plato;

import java.util.ArrayList;
import java.util.List;



public class DaoPlatos {

    private static final List<Plato> platos = new ArrayList<>();

    private void iniciar(){
        if(platos.isEmpty()){

            platos.add(new Plato("Milanesa", "dgdgdgdg", 56, 92.0));
            platos.add(new Plato("Albondigas", "dgdgdgdg", 56, 56.0));
            platos.add(new Plato("Sushi", "dgdgdgdg", 56, 145.0));
            platos.add(new Plato("Asado", "dgdgdgdg", 56, 200.0));
            platos.add(new Plato("Fideos", "dgdgdgdg", 56, 36.0));
            platos.add(new Plato("Lasagna", "dgdgdgdg", 56, 89.0));
            platos.add(new Plato("Pizza", "dgdgdgdg", 56, 178.0));
            platos.add(new Plato("Calzone", "dgdgdgdg", 56, 99.0));
            platos.add(new Plato("Ravioles", "dgdgdgdg", 56, 123.0));
            platos.add(new Plato("Napolitana", "dgdgdgdg", 56, 180.0));
            platos.add(new Plato("Empanada", "dgdgdgdg", 56, 69.0));
            platos.add(new Plato("Pescado", "dgdgdgdg", 56, 263.0));
            platos.add(new Plato("Fugazetta", "dgdgdgdg", 56, 300.0));
        }
    }

    public List<Plato> list(){
        iniciar();
        return this.platos;
    }



}
