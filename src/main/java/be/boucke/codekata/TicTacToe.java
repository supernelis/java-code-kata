package be.boucke.codekata;

import java.util.*;

import static be.boucke.codekata.Player.*;
import static be.boucke.codekata.Position.*;

public class TicTacToe {

    private GameFeedback gameFeedback = GameFeedback.gameStarted;
    private Player currentPlayer = PlayerX;
    private final Map<Position, Player> movesByPlayer = new HashMap<Position, Player>();

    public Player currentPlayer() {
        return currentPlayer;
    }

    public GameFeedback feedback() {
        return gameFeedback;
    }

    public void play(Position pos) {
        if (!isPositionFree(pos)) {
            gameFeedback = GameFeedback.positionAlreadytaken;
            return;
        }

        movesByPlayer.put(pos, currentPlayer);
        gameFeedback = GameFeedback.playSuccessfull;

        if (hasThreeInRow()) {
            gameFeedback = GameFeedback.gameEndedWithWin;
            return;
        }

        if (movesByPlayer.size() == 9) {
            gameFeedback = GameFeedback.gameEndedWithDraw;
        }

        switchPlayer();
    }

    private boolean isPositionFree(Position pos) {
        return !movesByPlayer.containsKey(pos);
    }

    private boolean hasThreeInRow() {
        return allMatch(getTopRow(), this.currentPlayer) ||
                allMatch(getMiddleRow(), this.currentPlayer) ||
                allMatch(getBottomRow(), this.currentPlayer) ||
                allMatch(getLeftColumn(), this.currentPlayer)||
                allMatch(getMiddleColumn(), this.currentPlayer)||
                allMatch(getRightColumn(), this.currentPlayer) ||
                allMatch(getSlashDownRow(), this.currentPlayer) ||
                allMatch(getSlashUpRow(), this.currentPlayer);

    }

    private List<Player> getSlashUpRow() {
        return getValuesAtPositions(bottomLeft, middleMiddle, topRight);
    }

    private List<Player> getSlashDownRow() {
        return getValuesAtPositions(topLeft, middleMiddle, bottomRight);
    }

    private List<Player> getRightColumn() {
        return getValuesAtPositions(topRight, middleRight, bottomRight);
    }

    private List<Player> getMiddleColumn() {
        return getValuesAtPositions(topMiddle, middleMiddle, bottomMiddle);
    }

    private void switchPlayer() {
        if (currentPlayer == PlayerX) {
            currentPlayer = PlayerO;
        } else {
            currentPlayer = PlayerX;
        }
    }

    private List<Player> getLeftColumn() {
        return getValuesAtPositions(topLeft, middleLeft, bottomLeft);
    }

    private List<Player> getBottomRow() {
        return getValuesAtPositions(bottomLeft, bottomMiddle, bottomRight);
    }

    private List<Player> getMiddleRow() {
        return getValuesAtPositions(middleLeft, middleMiddle, middleRight);
    }

    private List<Player> getTopRow() {
        return getValuesAtPositions(topLeft, topMiddle, topRight);
    }

    private boolean allMatch(List<Player> listOfValues, Player expectedValue) {
        return listOfValues.stream().allMatch(player -> expectedValue.equals(player));
    }

    private List<Player> getValuesAtPositions(Position... positions) {
        ArrayList<Player> values = new ArrayList<Player>();

        for (Position position : positions) {
            values.add(movesByPlayer.get(position));
        }

        return values;
    }
}
