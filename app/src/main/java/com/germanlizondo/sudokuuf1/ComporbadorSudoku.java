package com.germanlizondo.sudokuuf1;

public class ComporbadorSudoku {
    private Sudoku sudoku;
    private int[][] sudokuRespuesta;

    public ComporbadorSudoku(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    public boolean comprobarSudoku(){
        return true;
    }

    public boolean comprobarRow(int row){
        for(int x=0;x<9;x++){
            for(int y=0;y<9;y++){
                if(this.sudoku.getTablero()[row][x].getNumero()==this.sudoku.getTablero()[row][y].getNumero()){
                    return false;
                }
            }

        }
        return true;
    }

    public boolean comprobarFila(int fila){
        for(int x=0;x<9;x++){
            for(int y=0;y<9;y++){
                if(this.sudoku.getTablero()[x][fila].getNumero()==this.sudoku.getTablero()[x][fila].getNumero()){
                    return false;
                }
            }

        }
        return true;
    }

    public boolean comprobar3x3(int row,int column){

    /*    for(int x=0;x<3;x++){
            for(int y=0;y<3;y++){
                if(this.sudoku.getTablero()[x][fila].getNumero()==this.sudoku.getTablero()[x][fila].getNumero()){
                    return false;
                }
            }
            column++;
            row++;

        }

*/




        return true;
    }

    public Sudoku getSudoku() {
        return sudoku;
    }

    public void setSudoku(Sudoku sudoku) {
        this.sudoku = sudoku;
    }
}
