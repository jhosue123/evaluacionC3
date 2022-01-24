package com.example.evc3.Interfaces;

import com.example.evc3.Modelo.Rick;

import java.util.ArrayList;

public interface MainActivityView {
//almacena las tarjetas en una lista para luego mostrarlas
    void mostrarRick(ArrayList<Rick> tarjetasList);
    void buscarTarjeta(String name);
}
