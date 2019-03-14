package com.example.tpdm_u2_ejercicio2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    Abogado vector[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista=findViewById(R.id.listaabogado);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mostrarAlerta(position);
            }
        });

    }

    private void mostrarAlerta(final int pos) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);

        alerta.setTitle("Atencion")
                .setMessage("Deseas modificar/editar el abogado"+vector[pos].getNombre()+"?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        invocarEliminarActualizar(pos);
                    }
                })
                .setNegativeButton("no",null)
                .show();
    }

    private void invocarEliminarActualizar(int pos){
        Intent eliminaActualiza= new Intent(this,Main3Activity.class);
        eliminaActualiza.putExtra("id",vector[pos].getId());
        eliminaActualiza.putExtra("nombre",vector[pos].getNombre());
        eliminaActualiza.putExtra("telefono",vector[pos].getTelefono());
        eliminaActualiza.putExtra("sueldo",vector[pos].getSueldo());
        startActivity(eliminaActualiza);
    }

    @Override
    protected void onStart() {
        Abogado abogado=new Abogado(this);
        //vector[] = abogado.consultar();
        String[] nombreTelefono= null;

        if(vector== null){
            nombreTelefono = new String[1];
            nombreTelefono[0]="No hay abogados capturados";
        }else{
            nombreTelefono= new String[vector.length];
            for(int i=0;i<vector.length;i++){
                Abogado temp=vector[i];
                nombreTelefono[i]= temp.getNombre()+"\n"+temp.getTelefono();
            }
        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombreTelefono);

        lista.setAdapter(adaptador);

        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.nuevoabogado:
                Intent nuevoAbogado= new Intent(this,Main2Activity.class);
                startActivity(nuevoAbogado);
                break;

            case R.id.expedientes:
                Intent expediente= new Intent(this,Main3Activity.class);
                startActivity(expediente);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
