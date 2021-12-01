package sudoku.userinterface;

import sudoku.problemdomain.SudokuGame;

public interface IUserInterface {
  interface EventListener {
    void onSudokuInput(int x, int y, int input);
    void onButtonClick();
  }

  interface View {
    void setListener(IUserInterface.EventListener listener);
<<<<<<< HEAD
    void updateSquare(int x, int y, int input, Boolean condition);
=======
    void updateSquare(int x, int y, int input);
>>>>>>> 73fd1613d528a563c8a83b6e7f4e40493a7528d6
    void updateBoard(SudokuGame game);
    void showDialog(String message);
    void showError(String message);
  }
}
