package com.germanlizondo.sudokuuf1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.germanlizondo.sudokuuf1.Model.User;

public class SudokuGame extends AppCompatActivity {
    private User user;
    private Sudoku sudoku;
    private LinearLayout tablero;
    private EditText casilla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku_game);
        Bundle datos = this.getIntent().getExtras();
        user = new User();

        user.setNom(datos.getString("username").toString());
        user.setPunts(datos.getInt("punts"));

        this.sudoku = new Sudoku();


        this.tablero = (LinearLayout) findViewById(R.id.tablero);
        this.crearTablero();
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

            }
            this.tablero.addView(row);
        }
    }

    public void comporbarSudoku(View view) {

        for(int x =0;x<9;x++){

            for(int y =0;y<9;y++){


            }

        }
    }
}
