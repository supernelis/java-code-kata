package be.boucke.codekata;

public class TicTacToe {
    public String currentPlayer() {
        return "PlayerX";
    }

    public GameFeedback feedback() {
        return GameFeedback.positionAlreadytaken;
    }

    public void play(Positions pos) {

    }
}
