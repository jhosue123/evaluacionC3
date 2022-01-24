package com.example.evc3.Views;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.evc3.Adapter.Adapter;
import com.example.evc3.Interfaces.MainActivityView;
import com.example.evc3.Interfaces.PresenterView;
import com.example.evc3.Modelo.Rick;
import com.example.evc3.Presenter.Presenter;
import com.example.evc3.R;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements MainActivityView {

    ImageButton btn_log;

    RecyclerView recyclerView;
    EditText buscarEdit;
    private PresenterView presenter = new Presenter(this, MainActivity.this);
    public Adapter tarjetaAdapter;
    ImageView buscar;


    //metodo para instanciar las variables
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        buscar = findViewById(R.id.buscar);
        buscarEdit = findViewById(R.id.buscarEdit);

        btn_log=(ImageButton) findViewById(R.id.btn_log);

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               buscarTarjeta(buscarEdit.getText().toString());
                Toast.makeText(MainActivity.this, "Buscando...", Toast.LENGTH_SHORT).show();

            };
        });

        initView();

    }

    private void initView() {
        presenter.getTarjeta();
    }

    //metodo para mostrar las tarjetas
    @Override
    public void mostrarRick(ArrayList<Rick> tarjetasList) {

        tarjetaAdapter = new Adapter(tarjetasList, this);
        recyclerView.setAdapter(tarjetaAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void buscarTarjeta(String name) {
        presenter.buscarTarjeta(name);

    }
}