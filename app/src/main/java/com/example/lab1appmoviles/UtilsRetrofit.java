package com.example.lab1appmoviles;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UtilsRetrofit {
private String urlApiRest = "http://192.168.0.12:3001/";
private static UtilsRetrofit _INSTANCE;
public Retrofit retrofit;
   static UtilsRetrofit getInstance(){
        if(_INSTANCE==null){
            _INSTANCE = new UtilsRetrofit();
        }
        return _INSTANCE;
    }
    private UtilsRetrofit(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlApiRest)
                // En la siguiente linea, le especificamos a Retrofit que tiene que usar Gson para deserializar nuestros objetos
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        this.retrofit=retrofit;
    }

}
