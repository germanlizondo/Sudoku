package com.germanlizondo.sudokuuf1;

import android.util.Log;

import java.util.Random;

public class Sudoku {
    private Casilla[][] tablero = new Casilla[9][9];

    public Sudoku() {
        this.initTablero();
    }



    public void initTablero(){
        this.inicializarSudoku();
        this.llenarTableroConNumeros();
        this.intercambiarRows();
        this.intercambiarcolumnas();
        this.intercambiarMatrizesRow();
        this.intercambiarMatrizesColumn();
        this.printarTablero();


    }

    public void inicializarSudoku(){
        for(int x=0;x<9;x++){
            for(int y=0;y<9;y++){
                this.tablero[x][y] = new Casilla();
            }
        }
    }

    public void llenarTableroConNumeros() {
        int k = 1,n=1;
        for(int x=0;x<9;x++){
            k=n;
            for(int y=0;y<9;y++){
                if(k>9){
                    k=1;
                }
                this.tablero[x][y].setNumero(k);
                k++;
            }
            n=k+3;
            if(k==10)
                n=4;
            if(n>9)
                n=(n%9)+1;
        }
    }

    public void intercambiarRows(){
        Casilla[][] provisional = new Casilla[9][9];
        Random r= new Random();
        int k1,k2,max=2,min=0;
        for(int i=0;i<3;i++) {
            k1=r.nextInt(max-min+1)+min;
            do{
                k2=r.nextInt(max-min+1)+min;
            }while(k1==k2);

            for(int j=0;j<9;j++)
            {
                provisional[k1][j] = this.tablero[k1][j];
                this.tablero[k1][j] = this.tablero[k2][j];
                this.tablero[k2][j] = provisional[k1][j];
            }

            max+=3;min+=3;


        }
    }

    public void intercambiarcolumnas(){
        Casilla[][] provisional = new Casilla[9][9];
        Random r= new Random();
        int k1,k2,max=2,min=0;
        for(int i=0;i<3;i++) {
            k1=r.nextInt(max-min+1)+min;
            do{
                k2=r.nextInt(max-min+1)+min;
            }while(k1==k2);

            for(int j=0;j<9;j++)
            {
                provisional[j][k1] = this.tablero[j][k1];
                this.tablero[j][k1] = this.tablero[j][k2];
                this.tablero[j][k2] = provisional[j][k1];
            }

            max+=3;min+=3;


        }
    }

    public void intercambiarMatrizesRow(){
        int[] intArray = {0, 3, 6};
        int k1,k2;
        int idx = new Random().nextInt(intArray.length);
        k1 = intArray[idx];
        do{
            idx = new Random().nextInt(intArray.length);
            k2= intArray[idx];
        }while (k1==k2);
        Casilla[][] provisional = new Casilla[9][9];

        for(int n=1;n<=3;n++)
        {
            for(int i=0;i<9;i++)
            {
                provisional[k1][i] = this.tablero[k1][i];
                this.tablero[k1][i] = this.tablero[k2][i];
                this.tablero[k2][i] = provisional[k1][i];

            }
            k1++;
            k2++;
        }

    }

    public void intercambiarMatrizesColumn(){
        int[] intArray = {0, 3, 6};
        int k1,k2;
        int idx = new Random().nextInt(intArray.length);
        k1 = intArray[idx];
        do{
            idx = new Random().nextInt(intArray.length);
            k2= intArray[idx];
        }while (k1==k2);
        Casilla[][] provisional = new Casilla[9][9];

        for(int n=1;n<=3;n++)
        {
            for(int i=0;i<9;i++)
            {
                provisional[i][k1] = this.tablero[i][k1];
                this.tablero[i][k1] = this.tablero[i][k2];
                this.tablero[i][k2] = provisional[i][k1];
            }
            k1++;
            k2++;
        }

    }

    public void printarTablero(){
        for(int x=0;x<9;x++){
            for(int y=0;y<9;y++){
                System.out.print(this.tablero[x][y].getNumero());
            }
            System.out.println("");

        }
    }



    public Casilla[][] getTablero() {
        return tablero;
    }


    public void setTablero(Casilla[][] tablero) {
        this.tablero = tablero;
    }
}
