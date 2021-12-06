package sudoku.computationlogic;

import sudoku.konstanta.GameState;
import sudoku.konstanta.Rows;
import sudoku.problemdomain.SudokuGame;
import sudoku.problemdomain.Coordinates;

import java.util.*;

import javax.transaction.xa.Xid;

import static sudoku.problemdomain.SudokuGame.BATAS_GRID;;

public class GameLogic {
    //bikin attribut berupa list yang isinya koordinat angka yang sama. nantinya akan di oper2 
    public static List<Coordinates> wrongSquares = new ArrayList<>();

    public static SudokuGame getNewGame() {
        return new SudokuGame(
                GameState.NEW,
                GameGenerator.getNewGameGrid()
        );
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
        if (squaresAreInvalid(grid)) return true;
        if (rowsAreInvalid(grid)) return true;
        if (columnsAreInvalid(grid)) return true;
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

        int xTemp = xIndex;
        int yTemp = yIndex;

        List<Integer> square = new ArrayList<>();

        while (yTemp < yIndexEnd) {
            while (xTemp < xIndexEnd) {
                square.add(
                    grid[xTemp][yTemp]
                );

                xTemp++;
            }
            xTemp -= 3;

            yTemp++;
        }

        int repeatedNum = collectionHasRepeats(square);

        if (repeatedNum != -1) {
            for (int i = 0; i < square.size(); i++) {
                if(square.get(i) == repeatedNum){
                    wrongSquares.add(new Coordinates((i%3)+xIndex, (i/3)+yIndex));
                }
            }
            return true;
        }else {
            return false;
        }    
    }

    public static boolean columnsAreInvalid(int[][] grid) {
        for (int xIndex = 0; xIndex < BATAS_GRID; xIndex++) {
            List<Integer> column = new ArrayList<>();
            for (int yIndex = 0; yIndex < BATAS_GRID; yIndex++) {
                column.add(grid[xIndex][yIndex]);
            }

            int repeatedNum = collectionHasRepeats(column);

            if (repeatedNum != -1) {
                for (int i = 0; i < column.size(); i++) {
                    if(column.get(i) == repeatedNum){
                        wrongSquares.add(new Coordinates(xIndex, i));
                    }
                }
                return true;
            }
        }  

        return false;
    }

    public static boolean rowsAreInvalid(int[][] grid) {
        for (int yIndex = 0; yIndex < BATAS_GRID; yIndex++) {
            List<Integer> row = new ArrayList<>();
            for (int xIndex = 0; xIndex < BATAS_GRID; xIndex++) {
                row.add(grid[xIndex][yIndex]);
            }

            int repeatedNum = collectionHasRepeats(row);

            if (repeatedNum != -1) {
                for (int i = 0; i < row.size(); i++) {
                    if(row.get(i) == repeatedNum){
                        wrongSquares.add(new Coordinates(i, yIndex));
                    }
                }
                return true;
            }
        }

        return false;
    }

    public static int collectionHasRepeats(List<Integer> collection) {
        //method frequency akan mengembalikan jumlah elemen yang sama pada list
        for (int index = 1; index <= BATAS_GRID; index++) {
            if (Collections.frequency(collection, index) > 1) return index;
        }

        return -1;
    }
}