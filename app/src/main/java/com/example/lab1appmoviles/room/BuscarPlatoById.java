package com.example.lab1appmoviles.room;

import android.os.AsyncTask;

import com.example.lab1appmoviles.Plato;
import com.example.lab1appmoviles.dao.PlatoDao;

import java.util.List;

public class BuscarPlatoById extends AsyncTask<String, Void, List<Plato>> {
    private PlatoDao dao;
    private OnPlatoResultCallback callback;

    public BuscarPlatoById(PlatoDao platoDao, OnPlatoResultCallback context) {
        this.dao = dao;
        this.callback = context;
    }

    @Override
    protected List<Plato> doInBackground(String... strings) {
        List<Plato> platos = (List<Plato>) dao.buscar(strings[0]);
        return platos;
    }

    @Override
    protected void onPostExecute(List<Plato> platos) {
        super.onPostExecute(platos);
        callback.onResult(platos);
    }
}
