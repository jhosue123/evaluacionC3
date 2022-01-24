package com.example.evc3.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.evc3.R;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgdetalle;
    private TextView detalleNombre;
    private TextView detalleEstado;
    private TextView detalleEspecie;
    private TextView detalleId;
    private TextView detalleGender;
    ImageButton btn_home;


//metodo para inflar la vista detalle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);

        imgdetalle = findViewById(R.id.imgdetalle);
        detalleNombre = findViewById(R.id.detalleNombre);
        detalleEstado = findViewById(R.id.detalleEstado);
        detalleEspecie = findViewById(R.id.detalleEspecie);
        detalleId = findViewById(R.id.detalleId);
        detalleGender = findViewById(R.id.detalleGender);

        btn_home=(ImageButton) findViewById(R.id.btn_home);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DetailActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
       //se llama el metodo datos detalle
        datosDetalle();

    }
    //se captura los detalles de las tarjetas
    public void datosDetalle() {
        String nombre = getIntent().getStringExtra("Nombre");
        detalleNombre.setText(nombre);
        String estado = getIntent().getStringExtra("Estado");
        detalleEstado.setText(estado);
        String especie = getIntent().getStringExtra("Especie");
        detalleEspecie.setText(especie);
        String id = getIntent().getStringExtra("Id");
        detalleId.setText(id);
        String gender = getIntent().getStringExtra("Gender");
        detalleGender.setText(gender);
        String imagen = getIntent().getStringExtra("Imagen");
        Glide.with(this).load(imagen).into(imgdetalle);
    }




}