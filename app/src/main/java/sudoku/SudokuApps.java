package sudoku;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import sudoku.userinterface.IUserInterface;
import sudoku.userinterface.UserInterfaceImpl;
import sudoku.buildlogic.SudokuBuildLogic;

public class SudokuApps extends Application {
  private IUserInterface.View uiImpl;

  @Override
  public void start(Stage primaryStage) throws Exception {
    uiImpl = new UserInterfaceImpl(primaryStage);

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