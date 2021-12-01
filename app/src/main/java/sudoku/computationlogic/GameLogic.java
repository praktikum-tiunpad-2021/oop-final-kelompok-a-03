package sudoku.computationlogic;

import sudoku.konstanta.GameState;
import sudoku.konstanta.Rows;
import sudoku.problemdomain.SudokuGame;
import sudoku.problemdomain.Coordinates;

import java.util.*;

import static sudoku.problemdomain.SudokuGame.BATAS_GRID;;

public class GameLogic {
    //bikin attribut berupa list yang isinya koordinat angka yang sama. nantinya akan di oper2 
    private static List<Coordinates> wrongSquares = new ArrayList<>();

    public static SudokuGame getNewGame() {
        return new SudokuGame(
                GameState.NEW,
                GameGenerator.getNewGameGrid()
        );
    }

    public static List<Coordinates> getWrongSquares() {
        return wrongSquares;
    }

    //Memeriksa kondisi game saat ini (aktif atau selesai)
    public static GameState checkForCompletion(int[][] grid) {
        wrongSquares.clear();
        if (sudokuIsInvalid(grid)) return GameState.ACTIVE;
        if (tilesAreNotFilled(grid)) return GameState.ACTIVE;
        return GameState.COMPLETE;
    }

    //Memeriksa apabila masih terdapat petak kosong
    public static boolean tilesAreNotFilled(int[][] grid) {
        for (int xIndex = 0; xIndex < BATAS_GRID; xIndex++) {
            for (int yIndex = 0; yIndex < BATAS_GRID; yIndex++) {
                if (grid[xIndex][yIndex] == 0) return true;
            }
        }
        return false;
    }

    //Memeriksa apakah kondisi game valid (sesuai syarat sudoku) atau tidak setelah petak kosong diisi
    public static boolean sudokuIsInvalid(int[][] grid) {
        if (rowsAreInvalid(grid)) return true;
        if (columnsAreInvalid(grid)) return true;
        if (squaresAreInvalid(grid)) return true;
        else return false;
    }


    /*
        Sebuah "square" adalah kotak 3x3
        Example square:
        [0][0], [1][0], [2][0]
        [0][1], [1][1], [2][1]
        [0][2], [1][2], [2][2]
     */
    public static boolean squaresAreInvalid(int[][] grid) {
        //3 "square" atas
        if (rowOfSquaresIsInvalid(Rows.TOP, grid)) return true;

        //3 "square" tengah
        if (rowOfSquaresIsInvalid(Rows.MIDDLE, grid)) return true;

        //3 "square" bawah
        if (rowOfSquaresIsInvalid(Rows.BOTTOM, grid)) return true;

        return false;
    }

    private static boolean rowOfSquaresIsInvalid(Rows value, int[][] grid) {
        switch (value) {
            case TOP:
                if (squareIsInvalid(0, 0, grid)) return true;
                if (squareIsInvalid(0, 3, grid)) return true;
                if (squareIsInvalid(0, 6, grid)) return true;
                return false;

            case MIDDLE:
                if (squareIsInvalid(3, 0, grid)) return true;
                if (squareIsInvalid(3, 3, grid)) return true;
                if (squareIsInvalid(3, 6, grid)) return true;
                return false;

            case BOTTOM:
                if (squareIsInvalid(6, 0, grid)) return true;
                if (squareIsInvalid(6, 3, grid)) return true;
                if (squareIsInvalid(6, 6, grid)) return true;
                return false;

            default:
                return false;
        }
    }

    public static boolean squareIsInvalid(int yIndex, int xIndex, int[][] grid) {
        int yIndexEnd = yIndex + 3;
        int xIndexEnd = xIndex + 3;

        List<Integer> square = new ArrayList<>();

        while (yIndex < yIndexEnd) {

            while (xIndex < xIndexEnd) {
                if(square.contains(grid[xIndex][yIndex])){
                    wrongSquares.add(new Coordinates(xIndex, yIndex));
                }

                square.add(
                    grid[xIndex][yIndex]
                );

                xIndex++;
            }
            xIndex -= 3;

            yIndex++;
        }

        if (collectionHasRepeats(square)) {
            return true;
        }
        
        return false;
    }

    public static boolean columnsAreInvalid(int[][] grid) {
        for (int xIndex = 0; xIndex < BATAS_GRID; xIndex++) {
            List<Integer> column = new ArrayList<>();
            for (int yIndex = 0; yIndex < BATAS_GRID; yIndex++) {
<<<<<<< HEAD
                if(column.contains(grid[xIndex][yIndex])){
                    wrongSquares.add(new Coordinates(xIndex, yIndex));
                }

=======
>>>>>>> 73fd1613d528a563c8a83b6e7f4e40493a7528d6
                column.add(grid[xIndex][yIndex]);
            }

            if (collectionHasRepeats(column)) return true;
        }

        return false;
    }

    public static boolean rowsAreInvalid(int[][] grid) {
        for (int yIndex = 0; yIndex < BATAS_GRID; yIndex++) {
            List<Integer> row = new ArrayList<>();
            for (int xIndex = 0; xIndex < BATAS_GRID; xIndex++) {
                if(row.contains(grid[xIndex][yIndex])){
                    wrongSquares.add(new Coordinates(xIndex, yIndex));
                }
                row.add(grid[xIndex][yIndex]);
            }

            if (collectionHasRepeats(row)) return true;
        }

        return false;
    }

    public static boolean collectionHasRepeats(List<Integer> collection) {
        //method frequency akan mengembalikan jumlah elemen yang sama pada list
        for (int index = 1; index <= BATAS_GRID; index++) {
            if (Collections.frequency(collection, index) > 1) return true;
        }

        return false;
    }
}