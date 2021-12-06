package sudoku.userinterface.logic;


import sudoku.konstanta.GameState;
import sudoku.konstanta.Message;
import sudoku.computationlogic.GameLogic;
import sudoku.problemdomain.Coordinates;
import sudoku.problemdomain.IStorage;
import sudoku.problemdomain.SudokuGame;
import sudoku.userinterface.IUserInterface;

import java.util.*;

import java.io.IOException;

public class ControlLogic implements IUserInterface.EventListener {

    private IStorage storage;
    private IUserInterface.View view;

    public ControlLogic(IStorage storage, IUserInterface.View view) {
        this.storage = storage;
        this.view = view;
    }

    @Override
    public void onSudokuInput(int x, int y, int input) {
        try {
            SudokuGame gameData = storage.getGameData();
            int[][] newGridState = gameData.getCopyOfGridState();
            newGridState[x][y] = input;

            gameData = new SudokuGame(
                GameLogic.checkForCompletion(newGridState),
                newGridState
            );

            storage.updateGameData(gameData);
            view.updateSquare(x, y, input, GameLogic.sudokuIsInvalid(newGridState));
            
            view.updateBoard(gameData);

            if (gameData.getGameState() == GameState.COMPLETE) view.showDialog(Message.GAME_COMPLETE);
        } catch (IOException e) {
            e.printStackTrace();
            view.showError(Message.ERROR);
        }
    }

    @Override
    public void onButtonClick() {
        try {
            storage.updateGameData(
                    GameLogic.getNewGame()
            );

            view.updateBoard(storage.getGameData());
        } catch (IOException e) {
            view.showError(Message.ERROR);
        }
    }
}
