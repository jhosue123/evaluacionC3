package com.example.evc3.Interfaces;


import com.example.evc3.Modelo.Rick;

import java.util.ArrayList;

public interface PresenterView {
    void mostrarRick(ArrayList<Rick> tarjetasList);
    void getTarjeta();
    void buscarTarjeta(String name);
}
