package com.example.tpdm_u2_ejercicio2;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class Abogado {
    private BaseDatos base;
    private int id;
    private String nombre;
    private String telefono;
    private float sueldo;
    protected  String error;

    public Abogado(Activity activity){
        base = new BaseDatos(activity,"buffete",null,1);
    }

    public Abogado(int id,String nombre,String t,float s){
        this.id=id;
        this.nombre=nombre;
        telefono=t;
        sueldo=s;
    }

    public boolean insertar(Abogado abogado){
        try{
            SQLiteDatabase transaccionInsertar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("NOMBRE",abogado.getNombre());
            datos.put("TELEFONO",abogado.getTelefono());
            datos.put("SUELDO",abogado.getSueldo());
            long resultado = transaccionInsertar.insert("ABOGADO","IDABOGADO",datos);
            transaccionInsertar.close();
            if(resultado == -1)return false;
        }catch (SQLiteException e){
            error=e.getMessage();
            return false;
        }
        return true;
    }

    public Abogado[] consultar(String columna, String claveBusqueda){
        Abogado[] consultaAbogado=null;
        try{
            SQLiteDatabase trasaccionCosultar = base.getReadableDatabase();
            String SQL="SELECT * FROM ABOGADO WHERE IDABOGADO"+claveBusqueda;

            if(columna.startsWith("NOMBRE")){
                SQL = "SELECT * FROM ABOGADO WHERE NOMBRE = '"+claveBusqueda+"'";
            }
            if(columna.startsWith("SUELDO")){
                SQL = "SELECT * FROM ABOGADO WHERE SUELDO >= "+claveBusqueda;
            }

            Cursor c = trasaccionCosultar.rawQuery(SQL,null);
            if(c.moveToFirst()) {
                consultaAbogado= new Abogado[c.getCount()];
                int pos=0;
                do {
                    consultaAbogado[pos]=new Abogado(c.getInt(0), c.getString(1),
                            c.getString(2), c.getFloat(3));
                    pos++;
                } while (c.moveToNext());
            }
            trasaccionCosultar.close();
        }catch (SQLiteException e){
            error=e.getMessage();
            return null;
        }
        return consultaAbogado;
    }


    public boolean eliminar(Abogado abogado){
        int resultado;
        try{
            SQLiteDatabase transaccionEliminar = base.getWritableDatabase();
            String idAbogado= abogado.getId()+"";
            String claves[]={idAbogado};
            resultado=transaccionEliminar.delete("ABOGADO","idAbogado=?" ,claves);
            transaccionEliminar.close();
        }catch (SQLiteException e){
            error=e.getMessage();
            return false;
        }
        return resultado>0;
    }

    public boolean actualizar(Abogado abogado){
        try{
            SQLiteDatabase transaccionActualizar = base.getWritableDatabase();

            String idAbogado= abogado.getId()+"";
            String clave[]={idAbogado};
            ContentValues datos= new ContentValues();
            datos.put("nombre",abogado.getNombre());
            datos.put("telefono",abogado.getTelefono());
            datos.put("sueldo",abogado.getSueldo());

            long resultado = transaccionActualizar.update("ABOGADO",datos,"IDABOGADO = ?",clave);
            transaccionActualizar.close();
            if(resultado ==1)
                return false;

        }catch (SQLiteException e){
            error=e.getMessage();
            return false;
        }
        return true;
    }
    public BaseDatos getBase() {
        return base;
    }

    public void setBase(BaseDatos base) {
        this.base = base;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }


    public boolean consultar() {
        return true;
    }
}
