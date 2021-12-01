package sudoku.buildlogic;

import com.sun.javafx.iio.ios.IosDescriptor;
import sudoku.computationlogic.GameLogic;
import sudoku.persistence.LocalStorageImpl;
import sudoku.problemdomain.IStorage;
import sudoku.problemdomain.SudokuGame;
import sudoku.userinterface.IUserInterface;
import sudoku.userinterface.logic.ControlLogic;

import java.util.List;

import java.io.IOException;

public class SudokuBuildLogic {
    public static void build(IUserInterface.View userInterface) throws IOException {
        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();
            
        initialState = GameLogic.getNewGame();
        storage.updateGameData(initialState);

        IUserInterface.EventListener uiLogic = new ControlLogic(storage, userInterface);
        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
}
