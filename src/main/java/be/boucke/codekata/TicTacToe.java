package be.boucke.codekata;

public class TicTacToe {
    public Player currentPlayer() {
        return Player.PlayerX;
    }

    public GameFeedback feedback() {
        return GameFeedback.positionAlreadytaken;
    }

    public void play(Position pos) {

    }
}
