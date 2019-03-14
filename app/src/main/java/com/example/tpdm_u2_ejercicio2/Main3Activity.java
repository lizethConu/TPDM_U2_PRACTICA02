package com.example.tpdm_u2_ejercicio2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    EditText nombre,telefono,sueldo;
    Button actualizar, eliminar,regresar;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        nombre=findViewById(R.id.editanombre);
        telefono=findViewById(R.id.editatelefono);
        sueldo=findViewById(R.id.editasueldo);
        actualizar=findViewById(R.id.actualizarabogado);
        eliminar=findViewById(R.id.eliminarabogado);
        regresar=findViewById(R.id.regresarabogado);

        Bundle parametros= getIntent().getExtras();
        nombre.setText(parametros.getString("nombre"));
        telefono.setText(parametros.getString("telefono"));
        sueldo.setText(""+parametros.getFloat("sueldo"));
        id= parametros.getInt("id");

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void actualizar() {
        Abogado a= new Abogado(this);
        String mensaje;
        boolean respuesta = a.actualizar(new Abogado (id,nombre.getText().toString(),
                telefono.getText().toString(),Float.parseFloat(sueldo.getText().toString())
                ));

        if(respuesta){
            mensaje="se actualizo correctamente el abogado "+nombre.getText().toString();
        }else{
            mensaje="Error algo salio mal: "+a.error;
        }
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG);
    }
}
