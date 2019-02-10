package be.boucke.codekata;

import java.util.*;

import static be.boucke.codekata.Position.*;

public class TicTacToe {

    private GameFeedback gameFeedback = GameFeedback.gameStarted;
    private Player currentPlayer = Player.PlayerX;
    private final Map<Position, Player> moves = new HashMap<Position, Player>();

    public Player currentPlayer() {
        return currentPlayer;
    }

    public GameFeedback feedback() {
        return gameFeedback;
    }

    public void play(Position pos) {
        if (moves.containsKey(pos)) {
            gameFeedback = GameFeedback.positionAlreadytaken;
            return;
        }

        moves.put(pos, currentPlayer);
        gameFeedback = GameFeedback.playSuccessfull;

        if (hasThreeInRow()) {
            gameFeedback = GameFeedback.gameEndedWithWin;
            return;
        }

        if (moves.size() == 9) {
            gameFeedback = GameFeedback.gameEndedWithDraw;
        }

        switchPlayer();
    }

    private boolean hasThreeInRow() {
        return allMatch(getTopRow(), this.currentPlayer) ||
                allMatch(getMiddleRow(), this.currentPlayer) ||
                allMatch(getBottomRow(), this.currentPlayer) ||
                allMatch(getLeftColumn(), this.currentPlayer)||
                allMatch(getMiddleColumn(), this.currentPlayer);

    }

    private List<Player> getMiddleColumn() {
        return getValuesAtPositions(topMiddle, middleMiddle, bottomMiddle);
    }

    private void switchPlayer() {
        if (currentPlayer == Player.PlayerX) {
            currentPlayer = Player.PlayerO;
        } else {
            currentPlayer = Player.PlayerX;
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
            values.add(moves.get(position));
        }

        return values;
    }
}
