package com.example.lab1appmoviles.dao;

import com.example.lab1appmoviles.Plato;
import com.example.lab1appmoviles.PlatoApi;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PlatoService {
    @GET("platos/{id}")
    Call<PlatoApi> getPlato(@Path("id") String id);

    @GET("platos")
    Call<List<PlatoApi>> getPlatoList();

    @POST("platos")
    Call<PlatoApi> createPlato(@Body RequestBody body);
  /*
    Si deciden usar SendMeal-Fake-API deberán usar un body
    del tipo @Body String body. Lo cual les facilitara cumplir el formato esperado

    JSONObject bodyExample = new JSONObject();
    paramObject.put("email", "sample@gmail.com");
    paramObject.put("pass", "4384984938943");
    createPlato(bodyExample.toString())
  */
}
