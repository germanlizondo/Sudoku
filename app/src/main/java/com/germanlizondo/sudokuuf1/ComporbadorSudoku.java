package com.germanlizondo.sudokuuf1;

public class ComporbadorSudoku {
    private Sudoku sudoku;
    private Sudoku respuestaSudoku;

    public ComporbadorSudoku(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    public boolean comprobarSudoku(){

        for (int x=0;x<9;x++){
            for (int y=0;y<9;y++){
                if(this.sudoku.getTablero()[x][y].getNumero()!=this.respuestaSudoku.getTablero()[x][y].getNumero()) return false;
            }
        }
        return true;
    }


    public Sudoku getSudoku() {
        return sudoku;
    }

    public void setSudoku(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    public Sudoku getRespuestaSudoku() {
        return respuestaSudoku;
    }

    public void setRespuestaSudoku(Sudoku respuestaSudoku) {
        this.respuestaSudoku = respuestaSudoku;
    }
}
