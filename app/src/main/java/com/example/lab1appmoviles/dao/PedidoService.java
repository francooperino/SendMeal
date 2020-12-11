package com.example.lab1appmoviles.dao;

import com.example.lab1appmoviles.Pedido;
import com.example.lab1appmoviles.PedidoApi;
import com.example.lab1appmoviles.Plato;

import org.json.JSONObject;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PedidoService {
    @GET("pedidos/{id}")
    Call<PedidoApi> getPedido(@Path("id") String id);

    @GET("pedidos")
    Call<List<PedidoApi>> getPedidoList();

    @POST("pedidos")
    Call<PedidoApi> createPedido(@Body RequestBody body);
  /*
    Si deciden usar SendMeal-Fake-API deberán usar un body
    del tipo @Body String body. Lo cual les facilitara cumplir el formato esperado

    JSONObject bodyExample = new JSONObject();
    paramObject.put("email", "sample@gmail.com");
    paramObject.put("pass", "4384984938943");
    createPlato(bodyExample.toString())
  */
}
