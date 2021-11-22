package sudoku.userinteface;

import java.util.HashMap;

import javafx.animation.RotateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sudoku.konstanta.GameState;
import sudoku.problemdomain.Coordinates;
import sudoku.problemdomain.SudokuGame;
import sudoku.userinteface.IUserInterface.EventListener;

public class UserInterfaceImpl implements IUserInterface.View, EventHandler<KeyEvent> {

  private final Stage stage;
  private final Group root;
  private HashMap<Coordinates, SudokuTextField> textFieldCoordinates;

  private IUserInterface.EventListener listener;

  private static final double WINDOW_X = 668;
  private static final double WINDOW_Y = 732;

  private static final double PADDING_BOARD = 50;
  private static final double BOARD_X_Y = 50;

  private static final Color WARNA_BG_WINDOW = Color.rgb(0, 150, 136);
  private static final Color WARNA_BG_BOARD = Color.rgb(224, 242, 241);
  private static final String JUDUL = "Sudoku";

  public UserInterfaceImpl(Stage stage) {
    this.stage = stage;
    root = new Group();
    textFieldCoordinates = new HashMap<>();
    initUI();
  }

  private void initUI() {
    drawBackground(root);
    drawTitle(root);
    drawBoard(root);
    drawTextFields(root);
    drawGridLines(root);
    stage.show();
  }

  private void drawBackground(Group root) {
    Scene scene = new Scene(root, WINDOW_X, WINDOW_Y);
    scene.setFill(WARNA_BG_WINDOW);
    stage.setScene(scene);
  }

  private void drawTitle(Group root) {
    Text title = new Text(235, 690, JUDUL);
    title.setFill(Color.WHITE);
    Font titleFont = new Font(43);
    title.setFont(titleFont);
    root.getChildren().add(title);
  }

  private void drawBoard(Group root) {
    Rectangle boardBG = new Rectangle();
    boardBG.setX(PADDING_BOARD);
    boardBG.setY(PADDING_BOARD);
    boardBG.setWidth(BOARD_X_Y);
    boardBG.setHeight(BOARD_X_Y);

    boardBG.setFill(WARNA_BG_BOARD);
    root.getChildren().addAll(boardBG);
  }

  private void drawTextFields(Group root) {
    final int xAwal = 50;
    final int yAwal = 50;
    final int xDanYDelta = 64;

    for (int xIndex = 0; xIndex < 9; xIndex++) {
      for (int yIndex = 0; yIndex < 9; yIndex++) {
        int x = xAwal + xIndex * xDanYDelta;
        int y = yAwal + yIndex * xDanYDelta;

        SudokuTextField tile = new SudokuTextField(xIndex, yIndex);
        styleSudokuTile(tile, x, y);

        tile.setOnKeyPressed(this);
        textFieldCoordinates.put(new Coordinates(xIndex, yIndex), tile);
        root.getChildren().add(tile);
      }
    }
  }

  private void styleSudokuTile(SudokuTextField tile, int x, int y) {
    Font numberFont = new Font(32);
    tile.setFont(numberFont);
    tile.setAlignment(Pos.CENTER);
    tile.setLayoutX(x);
    tile.setLayoutY(y);
    tile.setPrefHeight(64);
    tile.setPrefWidth(64);

    tile.setBackground(Background.EMPTY);
  }

  private void drawGridLines(Group root) {
    int xDanY = 114;
    int index = 0;
    while (index < 8) {
      int tebal;
      if (index == 2 || index == 5) {
        tebal = 3;
      } else {
        tebal = 2;
      }
      Rectangle verticalLine = getLine(xDanY + 64 * index, PADDING_BOARD, BOARD_X_Y, tebal);
      Rectangle horizontalLine = getLine(PADDING_BOARD, xDanY + 64 * index, tebal, BOARD_X_Y);

      root.getChildren().addAll(verticalLine, horizontalLine);
      index++;
    }
  }

  private Rectangle getLine(double x, double y, double tinggi, double lebar) {
    Rectangle line = new Rectangle();

    line.setX(x);
    line.setY(y);
    line.setHeight(tinggi);
    line.setWidth(lebar);

    line.setFill(Color.BLACK);
    return line;
  }

  @Override
  public void handle(KeyEvent event) {
    if (event.getEventType() == KeyEvent.KEY_PRESSED) {
      if (event.getText().matches("0-9")) {
        int value = Integer.parseInt(event.getText());
        handleInput(value, event.getSource());
      } else if (event.getCode() == KeyCode.BACK_SPACE) {
        handleInput(0, event.getSource());
      } else {
        ((TextField) event.getSource()).setText("");
      }
    }
    event.consume();
  }

  private void handleInput(int value, Object source) {
    listener.onSudokuInput(((SudokuTextField) source).getX(), ((SudokuTextField) source).getY(), value);
  }

  @Override
  public void setListener(EventListener listener) {

    this.listener = listener;
  }

  @Override
  public void updateSquare(int x, int y, int input) {
    SudokuTextField tile = textFieldCoordinates.get(new Coordinates(x, y));
    String value = Integer.toString(input);
    if (value.equals("0")) {
      value = "";
    }

    tile.textProperty().setValue(value);
  }

  @Override
  public void updateBoard(SudokuGame game) {
    for (int xIndex = 0; xIndex < 9; xIndex++) {
      for (int yIndex = 0; yIndex < 9; yIndex++) {
        TextField tile = textFieldCoordinates.get(new Coordinates(xIndex, yIndex));
        String value = Integer.toString(game.getCopyGridState()[xIndex][yIndex]);

        if (value.equals("0")) {
          value = "";
        }

        tile.setText(value);
        if (game.getGameState() == GameState.NEW) {
          if (value.equals("")) {
            tile.setStyle("-fx-opacity: 1;");
            tile.setDisable(false);
          } else {
            tile.setStyle("-fx-opacity: 0.8;");
            tile.setDisable(true);
          }
        }
      }
    }

  }

  @Override
  public void showDialog(String message) {
    Alert dialog = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.OK);
    dialog.showAndWait();
    if (dialog.getResult() == ButtonType.OK) {
      listener.onDialogClick();
    }

  }

  @Override
  public void showError(String message) {
    Alert dialog = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
    dialog.showAndWait();

  }

}
