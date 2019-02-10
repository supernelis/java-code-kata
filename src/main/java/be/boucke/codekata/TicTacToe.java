package be.boucke.codekata;

import java.util.HashMap;
import java.util.Map;

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

        if (moves.size() == 9) {
            gameFeedback = GameFeedback.gameEndedWithDraw;
        }

        switchPlayer();
    }

    private void switchPlayer() {
        if (currentPlayer == Player.PlayerX) {
            currentPlayer = Player.PlayerO;
        } else {
            currentPlayer = Player.PlayerX;
        }
    }
}
