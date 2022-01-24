package com.example.evc3.Interfaces;

import com.example.evc3.Modelo.RickRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    //obtiene las tarjetas para mostrarlas en el menu
    @GET("api/character")
    Call<RickRespuesta> obtenerListaRick();

    @GET("api/character/?")
    Call<RickRespuesta> obtenerBusqueda(@Query("name") String name);
}
