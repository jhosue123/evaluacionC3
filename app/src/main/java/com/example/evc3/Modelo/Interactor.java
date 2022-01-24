package com.example.evc3.Modelo;

import android.content.Context;
import android.widget.Toast;

import com.example.evc3.Interfaces.InteractorView;
import com.example.evc3.Interfaces.PresenterView;
import com.example.evc3.Interfaces.Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Interactor implements InteractorView {
    private PresenterView presenter;
    private Context context;
    private Retrofit retrofit;
    private final String urlBase="https://rickandmortyapi.com/";
    private List<RickRespuesta> tarjetaModelList;

    public  Interactor(PresenterView presenter, Context context){
        this.presenter=presenter;
        this.context =context;
        this.tarjetaModelList=new ArrayList<>();
    }
    @Override
    public void obtenerDatos() {
         retrofit = new Retrofit.Builder()
                 .baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);
        Call<RickRespuesta> call = service.obtenerListaRick();
        call.enqueue(new Callback<RickRespuesta>() {

            @Override
            public void onResponse(Call<RickRespuesta> call, Response<RickRespuesta> response) {
                if(response.isSuccessful()) {
                    RickRespuesta rickRespuesta = response.body();
                    ArrayList<Rick> listaRick = rickRespuesta.getResults();
                    presenter.mostrarRick(listaRick);

                }else{
                    Toast.makeText(context, "Exito: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

            }
            @Override
            public void onFailure(Call<RickRespuesta> call, Throwable t) {
                Toast.makeText(context, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void getTarjeta() {
      obtenerDatos();

    }

    @Override
    public void buscarTarjeta(String name) {

        retrofit = new Retrofit.Builder()
                .baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);
        Call<RickRespuesta> call = service.obtenerBusqueda(name);
        call.enqueue(new Callback<RickRespuesta>() {

            @Override
            public void onResponse(Call<RickRespuesta> call, Response<RickRespuesta> response) {
                if (response.isSuccessful()) {
                    RickRespuesta rickRespuesta = response.body();
                    ArrayList<Rick> listaRick = rickRespuesta.getResults();
                    presenter.mostrarRick(listaRick);

                } else {
                    Toast.makeText(context, "Exito: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

            }

            @Override
            public void onFailure(Call<RickRespuesta> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
