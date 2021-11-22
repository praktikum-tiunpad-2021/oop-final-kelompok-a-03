// representasi visual dari game sudoku
package sudoku.problemdomain;

import sudoku.konstanta.GameState;
import java.io.Serializable;

public class SudokuGame implements Serializable {
  private final GameState gameState;
  private final int[][] gridState;

  public static final int BATAS_GRID = 9;

  public SudokuGame(GameState gameState, int[][] gridState) {
    this.gameState = gameState;
    this.gridState = gridState;
  }

  public GameState getGameState() {
    return gameState;
  }

  public int[][] getGridState() {
    return gridState;
  }
}