package com.germanlizondo.sudokuuf1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.germanlizondo.sudokuuf1.Controller.DatabaseSQLiteConexion;
import com.germanlizondo.sudokuuf1.Model.User;
import com.germanlizondo.sudokuuf1.Utilidades.Utilidades;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listaPunts;
    private PuntuacionesAdapter puntuacionesAdapter;
    private ArrayList<User> jugadors;
    private DatabaseSQLiteConexion conexionSQLite;
    private SQLiteDatabase db;

    private EditText nomJugadorInput;
    private Button buttonPlay;

    private String nomJugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.nomJugadorInput = (EditText) findViewById(R.id.inputNomJugador);
        this.nomJugadorInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().length()==0) buttonPlay.setEnabled(false);
                else buttonPlay.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        this.buttonPlay = (Button) findViewById(R.id.buttonPlay);
        this.buttonPlay.setEnabled(false);


        this.conexionSQLite = new DatabaseSQLiteConexion(this,"sudoku",null,1);

        this.listaPunts = (ListView) findViewById(R.id.listaPuntuacion);

    }

    @Override
    protected void onStart() {
        super.onStart();
        this.jugadors = new ArrayList<>();
        this.consultarBD();

        this.puntuacionesAdapter = new PuntuacionesAdapter(this,jugadors);
        this.listaPunts.setAdapter(this.puntuacionesAdapter);
    }

    public void consultarBD(){

        User usuario = null;
        this.db = this.conexionSQLite.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_NOM+","+Utilidades.CAMPO_PUNTS
                +" FROM "+Utilidades.NOMBRE_TABLA+" ORDER BY "+Utilidades.CAMPO_PUNTS+" DESC LIMIT 10",null);
        while (cursor.moveToNext()){

            usuario = new User();
            usuario.setNom(cursor.getString(0));
            usuario.setPunts(cursor.getInt(1));
            this.jugadors.add(usuario);
        }
        db.close();
    }

    public User buscaUsuario(String username){
        String[] parametros = {username};
        this.db = this.conexionSQLite.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_NOM+","+Utilidades.CAMPO_PUNTS
                +" FROM "+Utilidades.NOMBRE_TABLA+" WHERE "+Utilidades.CAMPO_NOM+" = ?",parametros);
        if(cursor.getCount()==0)    return null;
        else{
            User user = new User();
           cursor.moveToFirst();
                user.setNom(cursor.getString(cursor.getColumnIndex(Utilidades.CAMPO_NOM)));
                user.setPunts(cursor.getInt(cursor.getColumnIndex(Utilidades.CAMPO_PUNTS)));
            return user;
        }

    }


    public void playSudoku(View view) {
        this.nomJugador = this.nomJugadorInput.getText().toString();

        User user = this.buscaUsuario(this.nomJugador);
        this.nomJugadorInput.setText("");
        if(user==null){
            this.db = this.conexionSQLite.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_NOM,this.nomJugador);
            Long insert = db.insert(Utilidades.NOMBRE_TABLA,Utilidades.CAMPO_NOM,values);
            db.close();
            user = this.buscaUsuario(this.nomJugador);


        }



        Intent intent = new Intent(this, SudokuGame.class);
        intent.putExtra("username", user.getNom());
        intent.putExtra("punts", user.getPunts());
        startActivity(intent);




    }



}
