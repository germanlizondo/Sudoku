package com.germanlizondo.sudokuuf1.Controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.germanlizondo.sudokuuf1.Utilidades.Utilidades;

public class DatabaseSQLiteConexion extends SQLiteOpenHelper {


    public DatabaseSQLiteConexion(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Utilidades.CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
