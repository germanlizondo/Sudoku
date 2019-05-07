package com.germanlizondo.sudokuuf1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.germanlizondo.sudokuuf1.Controller.DatabaseSQLiteConexion;
import com.germanlizondo.sudokuuf1.Model.User;
import com.germanlizondo.sudokuuf1.Utilidades.Utilidades;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class SudokuGame extends AppCompatActivity {
    private User user;
    private Sudoku sudoku;
    private LinearLayout tablero;
    private EditText casilla;
    private TextView contador;
    private ComporbadorSudoku solver;
    private HashMap<Integer,EditText> casillasMap;
    private Timer timer;
    private int segundos;
    private int minutos;
    private int horas;
    private int count;
    private DatabaseSQLiteConexion conexionSQLite;
    private static final int EVENT_AFEGIT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku_game);
        Bundle datos = this.getIntent().getExtras();
        user = new User();

        user.setNom(datos.getString("username").toString());
        user.setPunts(datos.getInt("punts"));

        this.sudoku = new Sudoku();
        this.solver = new ComporbadorSudoku(this.sudoku);
        this.casillasMap = new HashMap<>();

        this.tablero = (LinearLayout) findViewById(R.id.tablero);
        this.contador = (TextView) findViewById(R.id.contador);
        this.timer = new Timer();
        this.crearTablero();
        this.empezarContadorTiempo();

        this.conexionSQLite = new DatabaseSQLiteConexion(this,"sudoku",null,1);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menuoverflow,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.comjugar:
                startActivity(new Intent(this,ComJugarActivity.class));
        }

        return super.onOptionsItemSelected(item);

    }


    public void crearTablero(){

        for(int x =0;x<9;x++){
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            for(int y =0;y<9;y++){
                casilla = new EditText(this);
                casilla.setInputType(InputType.TYPE_CLASS_NUMBER);
                casilla.setText(this.sudoku.getTablero()[x][y].getNumero()+"");
                casilla.setLayoutParams(new LinearLayout.LayoutParams(150, LinearLayout.LayoutParams.WRAP_CONTENT));

                String concatenacionid = x+""+y;
                int id = Integer.parseInt(concatenacionid);
                casilla.setId(id);
                row.addView(casilla);
                this.casillasMap.put(id,casilla);

            }
            this.tablero.addView(row);
        }
    }

    public void comporbarSudoku(View view) {
        this.recorrerSudoku();

      if(this.solver.comprobarSudoku()){
          Toast.makeText(this,"OL RIGHT",Toast.LENGTH_SHORT).show();
          this.calcularPuntuacion();
          this.insertarPuntos();
          this.insertarPatidaCalendari();
      }else {
          Toast.makeText(this,"BIG TROUBLE",Toast.LENGTH_SHORT).show();

      }
    }

    public void recorrerSudoku(){
        EditText casilla;
         for(int x=0;x<9;x++){
            for(int y=0;y<9;y++){
                casilla = this.casillasMap.get(Integer.parseInt(x+""+y));
                this.sudoku.getTablero()[x][y].setNumero(Integer.parseInt(casilla.getText().toString()));

            }

        }
    }

    public void calcularPuntuacion(){
      if(count>3600){
          this.user.setPunts(10);
      }else if(count>1800&&count<3600){
          this.user.setPunts(20);
      }else if(count>900&&count<180){
          this.user.setPunts(40);
      }else if(count>600&&count<900){
          this.user.setPunts(80);
      }else if(count>300&&count<900){
          this.user.setPunts(160);
      }else{
          this.user.setPunts(320);
      }
    }

    public void insertarPuntos(){
        SQLiteDatabase db = this.conexionSQLite.getWritableDatabase();
        String[] args = {this.user.getNom()};
        db.execSQL("UPDATE users SET punts ="+this.user.getPunts()+" WHERE nom = ?",args);
        db.close();
    }


    public void empezarContadorTiempo(){
      segundos = 0;
      minutos = 0;
      horas = 0;
      count = 0;
       this.timer.scheduleAtFixedRate(new TimerTask() {
           @Override
           public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(segundos==60){
                            segundos = 0;
                            minutos++;
                        }

                        if(minutos == 60){
                            minutos = 0;
                            horas++;
                        }
                       contador.setText(horas+"h "+minutos+"m "+segundos +"s");
                       segundos++;
                       count++;
                    }
                });
           }
       },1000,1000);
    }

    private void insertarPatidaCalendari() {

        Calendar calendari = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", calendari.getTimeInMillis());
        intent.putExtra("allDay", true);
        intent.putExtra("rrule", "FREQ=YEARLY");
        intent.putExtra("endTime", calendari.getTimeInMillis() + 60 * 60 * 1000);
        intent.putExtra("title", "Partida Sudoku");
        intent.putExtra("description", this.user.getNom()+" | "+this.user.getPunts()+" punts");
        intent.putExtra("eventLocation", "BARCELONA");

        startActivityForResult(intent,EVENT_AFEGIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EVENT_AFEGIT) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "S'ha afegit al Calendari la Partida", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }


}
