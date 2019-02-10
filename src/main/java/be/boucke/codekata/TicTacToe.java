package be.boucke.codekata;

public class TicTacToe {

    private Player currentPlayer = Player.PlayerX;

    public Player currentPlayer() {
        return currentPlayer;
    }

    public GameFeedback feedback() {
        return GameFeedback.positionAlreadytaken;
    }

    public void play(Position pos) {

        if (currentPlayer == Player.PlayerX) {
            currentPlayer = Player.PlayerO;
        }else{
            currentPlayer = Player.PlayerX;
        }

    }
}
