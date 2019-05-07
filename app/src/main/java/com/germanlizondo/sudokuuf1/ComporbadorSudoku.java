package com.germanlizondo.sudokuuf1;

public class ComporbadorSudoku {
    private Sudoku sudoku;

    public ComporbadorSudoku(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    public boolean comprobarSudoku(){

        for (int x=0;x<9;x++){
            if (!this.comprobarFila(x)&& !this.comprobarRow(x)) return false;
        }
        return true;
    }

    public boolean comprobarRow(int row){
        for(int x=0;x<9;x++){
            for(int y=x+1;y<9;y++){
                if(this.sudoku.getTablero()[row][x].getNumero()==this.sudoku.getTablero()[row][y].getNumero()){
                    return false;
                }
            }

        }
        return true;
    }

    public boolean comprobarFila(int fila){
        for(int x=0;x<9;x++){
            for(int y=x+1;y<9;y++){
                if(this.sudoku.getTablero()[x][fila].getNumero()==this.sudoku.getTablero()[y][fila].getNumero()){
                    return false;
                }
            }

        }
        return true;
    }

    public boolean comprobar3x3fila(int row,int column){

        for(int x=0;x<3;x++){
            for(int y=0;y<3;y++){
                if(this.sudoku.getTablero()[row][x].getNumero()==this.sudoku.getTablero()[row][y].getNumero()){
                    return false;
                }
            }
            row++;
            column++;

        }

        return true;
    }

    public Sudoku getSudoku() {
        return sudoku;
    }

    public void setSudoku(Sudoku sudoku) {
        this.sudoku = sudoku;
    }
}
