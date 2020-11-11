package com.example.lab1appmoviles.room;

import android.os.AsyncTask;

import com.example.lab1appmoviles.Pedido;
import com.example.lab1appmoviles.dao.PedidoDao;


import java.util.List;

// android.os.AsyncTask<Params, Progress, Result>
class BuscarTodosPedidos extends AsyncTask<String, Void, List<Pedido>> {

    private PedidoDao dao;
    private OnObjectResultCallback callback;

    public BuscarTodosPedidos(PedidoDao dao, OnObjectResultCallback context) {
        this.dao = dao;
        this.callback = context;
    }

    @Override
    protected List<Pedido> doInBackground(String... strings) {
        List<Pedido> pedidos = dao.buscarTodos();
        return pedidos;
    }

    @Override
    protected void onPostExecute(List<Pedido> pedidos) {
        super.onPostExecute(pedidos);
        List objeto = (List) pedidos;
        callback.onResult(objeto);
    }
}
