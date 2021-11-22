package sudoku.userinterface;

import sudoku.problemdomain.SudokuGame;

public interface IUserInterface {
  interface EventListener {
    void onSudokuInput(int x, int y, int input);

    void onDialogClick();
  }

  interface View {
    void setListener(IUserInterface.EventListener listener);

    void updateSquare(int x, int y, int input);

    void updateBoard(SudokuGame game);

    void showDialog(String message);

    void showError(String message);
  }
}
