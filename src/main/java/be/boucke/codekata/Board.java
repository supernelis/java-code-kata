package be.boucke.codekata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static be.boucke.codekata.Position.*;

public class Board {

    private final Map<Position, Player> movesByPlayer = new HashMap<Position, Player>();

    boolean allSquaresFilled() {
        return movesByPlayer.size() == 9;
    }

    void addMove(Position pos, Player player) {
        movesByPlayer.put(pos, player);
    }

    boolean isPositionFree(Position pos) {
        return !movesByPlayer.containsKey(pos);
    }

    List<Player> getValuesAtPositions(Position... positions) {
        ArrayList<Player> values = new ArrayList<Player>();

        for (Position position : positions) {
            values.add(movesByPlayer.get(position));
        }

        return values;
    }

    List<Player> getSlashUpRow(TicTacToe ticTacToe) {
        return getValuesAtPositions(bottomLeft, middleMiddle, topRight);
    }

    List<Player> getSlashDownRow(TicTacToe ticTacToe) {
        return getValuesAtPositions(topLeft, middleMiddle, bottomRight);
    }

    List<Player> getRightColumn(TicTacToe ticTacToe) {
        return getValuesAtPositions(topRight, middleRight, bottomRight);
    }

    List<Player> getMiddleColumn(TicTacToe ticTacToe) {
        return getValuesAtPositions(topMiddle, middleMiddle, bottomMiddle);
    }

    List<Player> getLeftColumn(TicTacToe ticTacToe) {
        return getValuesAtPositions(topLeft, middleLeft, bottomLeft);
    }

    List<Player> getBottomRow(TicTacToe ticTacToe) {
        return getValuesAtPositions(bottomLeft, bottomMiddle, bottomRight);
    }

    List<Player> getMiddleRow(TicTacToe ticTacToe) {
        return getValuesAtPositions(middleLeft, middleMiddle, middleRight);
    }

    List<Player> getTopRow(TicTacToe ticTacToe) {
        return getValuesAtPositions(topLeft, topMiddle, topRight);
    }
}
