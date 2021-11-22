package sudoku;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import sudoku.userinterface.IUserInterface;
import sudoku.userinterface.UserInterfaceImpl;

public class SudokuApps extends Application {
  private IUserInterface.View uiImpl;

  @Override
  public void start(Stage stage) throws Exception {
    uiImpl = new UserInterfaceImpl(stage);
  }

  public static void main(String[] args) {
    launch();
  }

}