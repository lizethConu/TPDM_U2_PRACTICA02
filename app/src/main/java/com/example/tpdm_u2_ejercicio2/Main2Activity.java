package com.example.tpdm_u2_ejercicio2;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView nombre, sueldo,telefono;
    Button insertar,regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        sueldo=findViewById(R.id.sueldo);
        nombre=findViewById(R.id.nombre);
        telefono=findViewById(R.id.telefono);
        insertar=findViewById(R.id.insertar);
        regresar= findViewById(R.id.regresar);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void insertar() {
        String mensaje="";
        Abogado abogado=new Abogado(this);
        boolean respuesta = abogado.insertar(new Abogado(-1,nombre.getText().toString(),
                telefono.getText().toString(),Float.parseFloat(sueldo.getText().toString())));
        if(respuesta){
            mensaje= "se pudo insertar el abogado "+nombre.getText().toString();
        }
        else{
            mensaje="Error no se pudo crear el abogado, respuesta de SQLite"+abogado.error;
        }
        AlertDialog.Builder alerta =new AlertDialog.Builder(this);
        alerta.setTitle("ATENCION").setMessage(mensaje).setPositiveButton("ok",null).show();
    }


}
