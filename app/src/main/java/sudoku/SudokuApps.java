package sudoku;

import java.io.IOException;
import sudoku.userinteface.IUserInterface;
import sudoku.userinteface.UserInterfaceImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class SudokuApps extends Application {
  private IUserInterface.View uiImpl;

  @Override
  public void start(Stage stage) throws Exception {
    uiImpl = new UserInterfaceImpl(stage);
    try {
      SudokuBuildLogic.build(uiImpl);
    } catch (IOException e) {
      e.printStackTrace();
      throw e;
    }
  }

  public static void main(String[] args) {
    launch();
  }

}