package com.example.evc3.Presenter;

import android.content.Context;

import com.example.evc3.Interfaces.InteractorView;
import com.example.evc3.Interfaces.MainActivityView;
import com.example.evc3.Interfaces.PresenterView;
import com.example.evc3.Modelo.Interactor;
import com.example.evc3.Modelo.Rick;

import java.util.ArrayList;

public class Presenter implements PresenterView {


    private MainActivityView view;
    private InteractorView interactor;

    //metodo para comunicar la interfaz mainactivity con el interactor
    public Presenter(MainActivityView view, Context context) {
        this.view = view;
        interactor=new Interactor(this,context);
    }


    //metodo para obtener los datos

    @Override
    public void mostrarRick(ArrayList<Rick> tarjetasList) {
        view.mostrarRick(tarjetasList);
    }

    @Override
    public void getTarjeta() {
        interactor.getTarjeta();

    }

    @Override
    public void buscarTarjeta(String name) {
     interactor.buscarTarjeta(name);
    }


}
