package com.example.lab1appmoviles.room;

import android.os.AsyncTask;

import com.example.lab1appmoviles.Plato;
import com.example.lab1appmoviles.dao.PlatoDao;

import java.util.List;

// android.os.AsyncTask<Params, Progress, Result>
class BuscarPlatos extends AsyncTask<String, Void, List<Plato>> {

    private PlatoDao dao;
    private OnPlatoResultCallback callback;

    public BuscarPlatos(PlatoDao dao, OnPlatoResultCallback context) {
        this.dao = dao;
        this.callback = context;
    }

    @Override
    protected List<Plato> doInBackground(String... strings) {
        List<Plato> platos = dao.buscarTodos();
        return platos;
    }

    @Override
    protected void onPostExecute(List<Plato> platos) {
        super.onPostExecute(platos);
        callback.onResult(platos);
    }
}
