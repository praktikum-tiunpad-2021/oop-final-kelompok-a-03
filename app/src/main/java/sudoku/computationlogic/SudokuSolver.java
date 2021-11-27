package sudoku.computationlogic;

import sudoku.konstanta.GameState;
import sudoku.problemdomain.Coordinates;
import sudoku.problemdomain.SudokuGame;

import static sudoku.problemdomain.SudokuGame.BATAS_GRID;

public class SudokuSolver {

    /**
     * 1.Menghitung jumlah petak kosong pada grid.
     * 2.Current cell mengiterasi setiap petak kosong.
     * 3.Memasukkan 1 pada current cell. Jika tidak valid, maka masukan 2, 3, ..., 9 hingga valid
     * Jika setelah input 9 masih tidak valid,
     * If not, then erase the 9 from the current cell, call the previous cell in the enumeration the new “current cell”, and
     * continue with step 3.
     */
    public static boolean puzzleIsSolvable(int[][] puzzle) {

        //step 1:
        Coordinates[] emptyCells = typeWriterEnumerate(puzzle);

        int index = 0;
        int input = 1;
        while (index < 40) {
            Coordinates current = emptyCells[index];
            input = 1;
            while (input < 10) {
                puzzle[current.getX()][current.getY()] = input;
                if (GameLogic.sudokuIsInvalid(puzzle)) {
                    if (index == 0 && input == BATAS_GRID) {
                        return false;
                    } else if (input == BATAS_GRID) {
                        index--;
                    }

                    input++;
                } else {
                    index++;

                    if (index == 39) {
                        return true;
                    }

                    //break loop
                    input = 10;
                }
            }
        }

        return false;
    }

    private static Coordinates[] typeWriterEnumerate(int[][] puzzle) {
        Coordinates[] emptyCells = new Coordinates[40];
        int iterator = 0;
        for (int y = 0; y < BATAS_GRID; y++) {
            for (int x = 0; x < BATAS_GRID; x++) {
                if (puzzle[x][y] == 0) {
                    emptyCells[iterator] = new Coordinates(x, y);
                    if (iterator == 39) return emptyCells;
                    iterator++;
                }
            }
        }
        return emptyCells;
    }


}