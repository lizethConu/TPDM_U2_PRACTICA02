package com.example.tpdm_u2_ejercicio2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {
    public BaseDatos(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//se ejecuta primera vez
        db.execSQL("CREATE TABLE ABOGADO( IDABOGADO INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NOMBRE VARCHAR(200),TELEFONO VARCHAR (50),SUELDO FLOAT)");
        db.execSQL("CREATE TABLE EXPEDIENTE( IDEXPEDIENTE VARCHAR(500) PRIMARY KEY NOT NULL," +
                " DESCRIPCION VARCHAR(500),CLIENTE VARCHAR(500), FECHA DATE," +
                "FORANEA INTEGER," +
                "FOREIGN KEY (FORANEA) REFERENCES ABOGADO(IDABOGADO))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//Cuando hay un cambio de version

    }
}
