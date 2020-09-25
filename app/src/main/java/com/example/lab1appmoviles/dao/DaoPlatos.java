package com.example.lab1appmoviles.dao;

import android.util.Log;

import com.example.lab1appmoviles.Plato;

import java.util.ArrayList;
import java.util.List;



public class DaoPlatos {

    private static final List<Plato> platos = new ArrayList<>();

    private void iniciar(){
        if(platos.isEmpty()){

            platos.add(new Plato("milanesa", "dgdgdgdg", 56, 92.0));
            platos.add(new Plato("albondigas", "dgdgdgdg", 56, 56.0));
            platos.add(new Plato("sushi", "dgdgdgdg", 56, 145.0));
            platos.add(new Plato("asado", "dgdgdgdg", 56, 200.0));
            platos.add(new Plato("fideos", "dgdgdgdg", 56, 36.0));
            platos.add(new Plato("lasagna", "dgdgdgdg", 56, 89.0));
            platos.add(new Plato("pizza", "dgdgdgdg", 56, 178.0));
            platos.add(new Plato("calzone", "dgdgdgdg", 56, 99.0));
            platos.add(new Plato("ravioles", "dgdgdgdg", 56, 123.0));
            platos.add(new Plato("napolitana", "dgdgdgdg", 56, 180.0));
            platos.add(new Plato("empanada", "dgdgdgdg", 56, 69.0));
            platos.add(new Plato("pescado", "dgdgdgdg", 56, 263.0));
            platos.add(new Plato("fugazetta", "dgdgdgdg", 56, 300.0));
        }
    }

    public List<Plato> list(){
        iniciar();
        return this.platos;
    }


}
