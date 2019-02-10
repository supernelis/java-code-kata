package be.boucke.codekata;

import java.util.*;

import static be.boucke.codekata.Player.*;

public class TicTacToe {

    private GameFeedback gameFeedback = GameFeedback.gameStarted;
    private Player currentPlayer = PlayerX;
    private Board board = new Board();

    public Player currentPlayer() {
        return currentPlayer;
    }

    public GameFeedback feedback() {
        return gameFeedback;
    }

    public void play(Position pos) {
        if (!board.isPositionFree(pos)) {
            gameFeedback = GameFeedback.positionAlreadytaken;
            return;
        }

        Player player = this.currentPlayer;
        board.addMove(pos, player);
        gameFeedback = GameFeedback.playSuccessfull;

        if (hasThreeInRow()) {
            gameFeedback = GameFeedback.gameEndedWithWin;
            return;
        }

        if (board.allSquaresFilled()) {
            gameFeedback = GameFeedback.gameEndedWithDraw;
        }

        switchPlayer();
    }



    private boolean hasThreeInRow() {
        return allMatch(board.getTopRow(this), this.currentPlayer) ||
                allMatch(board.getMiddleRow(this), this.currentPlayer) ||
                allMatch(board.getBottomRow(this), this.currentPlayer) ||
                allMatch(board.getLeftColumn(this), this.currentPlayer)||
                allMatch(board.getMiddleColumn(this), this.currentPlayer)||
                allMatch(board.getRightColumn(this), this.currentPlayer) ||
                allMatch(board.getSlashDownRow(this), this.currentPlayer) ||
                allMatch(board.getSlashUpRow(this), this.currentPlayer);

    }

    private void switchPlayer() {
        if (currentPlayer == PlayerX) {
            currentPlayer = PlayerO;
        } else {
            currentPlayer = PlayerX;
        }
    }

    private boolean allMatch(List<Player> listOfValues, Player expectedValue) {
        return listOfValues.stream().allMatch(player -> expectedValue.equals(player));
    }


}
