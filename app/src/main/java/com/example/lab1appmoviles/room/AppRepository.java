package com.example.lab1appmoviles.room;

import android.app.Application;
import android.util.Log;

import com.example.lab1appmoviles.Pedido;
import com.example.lab1appmoviles.Plato;
import com.example.lab1appmoviles.dao.PedidoDao;
import com.example.lab1appmoviles.dao.PlatoDao;

import java.util.List;

public class AppRepository implements OnObjectResultCallback{
    private PlatoDao platoDao;
    private PedidoDao pedidoDao;
    private OnResultCallback callback;
    private Plato plato;
    private Pedido pedido;

    public AppRepository(Application application, OnResultCallback context){
        AppDatabase db = AppDatabase.getInstance(application);
        platoDao = db.platoDao();
        pedidoDao = db.pedidoDao();
        callback = context;
    }

    public void insertar(final Object objeto){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if(objeto instanceof Plato){
                    plato= (Plato) objeto;
                    platoDao.insertar(plato);
                    Log.d("DEBUG", "Plato found");
                }
                else{
                    pedido= (Pedido) objeto;
                    pedidoDao.insertar(pedido);
                }
            }
        });
    }

    public void borrar(final Object objeto){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if(objeto instanceof Plato){
                    plato= (Plato) objeto;
                    platoDao.borrar(plato);
                    Log.d("DEBUG", "Plato found");
                }
                else{
                    pedido= (Pedido) objeto;
                    pedidoDao.borrar(pedido);
                }
            }
        });
    }

    public void actualizar(final Object objeto){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if(objeto instanceof Plato){
                    plato= (Plato) objeto;
                    platoDao.actualizar(plato);
                    Log.d("DEBUG", "Plato found");
                }
                else{
                    pedido= (Pedido) objeto;
                    pedidoDao.actualizar(pedido);
                }
            }
        });
    }

    public void borrarPlatos(){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
             platoDao.borrarPlatos();
            }
        });
    }
    public void borrarPedidos(){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                pedidoDao.borrarPedidos();
            }
        });
    }

    public void buscar(String id) {
        new BuscarPlatoById(platoDao, this).execute(id);
    }

    public void buscarTodos() {
        new BuscarPlatos(platoDao, this).execute();
    }
    public void buscarTodosLosPedidos() {
        new BuscarTodosPedidos(pedidoDao, this).execute();
    }

   /* @Override
    public void onResult(List<Plato> platos) {
        Log.d("DEBUG", "Plato found");
        callback.onResult(platos);
    }*/

    @Override
    public void onResult(List<Object> objeto) {
        callback.onResult(objeto);
    }


    public interface OnResultCallback<T> {
        void onResult(List<T> result);
    }
}
