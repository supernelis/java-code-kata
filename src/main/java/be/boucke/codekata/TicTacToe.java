package be.boucke.codekata;

import java.util.*;

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
        return getTopRow().stream().allMatch(player -> currentPlayer.equals(player)) ||
                getMiddleRow().stream().allMatch(player -> currentPlayer.equals(player)) ||
                getBottomRow().stream().allMatch(player -> currentPlayer.equals(player));

    }

    private List<Player> getBottomRow() {
        return getValuesAtPositions(Position.bottomLeft,Position.bottomMiddle,Position.bottomRight);
    }

    private List<Player> getMiddleRow() {
        return getValuesAtPositions(Position.middleLeft,Position.middleMiddle,Position.middleRight);
    }

    private List<Player> getTopRow() {
        return getValuesAtPositions(Position.topLeft,Position.topMiddle,Position.topRight);
    }

    private void switchPlayer() {
        if (currentPlayer == Player.PlayerX) {
            currentPlayer = Player.PlayerO;
        } else {
            currentPlayer = Player.PlayerX;
        }
    }

    private List<Player> getValuesAtPositions(Position ... positions){
        ArrayList<Player> values = new ArrayList<Player>();

        for (Position position : positions) {
            values.add(moves.get(position));
        }

        return values;
    }
}
