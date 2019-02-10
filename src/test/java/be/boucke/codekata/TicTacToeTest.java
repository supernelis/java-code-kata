package be.boucke.codekata;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static be.boucke.codekata.Position.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class TicTacToeTest {

    private TicTacToe game;

    @Before
    public void setUp() throws Exception {
        game = new TicTacToe();
    }

    @Test
    public void aGameCanBeStarted() {
        assertThatGameFeedbackEquals(GameFeedback.gameStarted);
    }

    @Test
    public void xAlwaysGoesFirst() {
        assertThatCurrentPlayerEqualsTo(Player.PlayerX);
    }

    @Test
    public void playersCannotPlayOnAPlayedPosition() {
        Position pos = bottomLeft;
        game.play(pos);
        game.play(pos);

        assertThatGameFeedbackEquals(GameFeedback.positionAlreadytaken);
    }

    @Test
    public void playersCanPlayOnAnEmptyPosition() {
        game.play(bottomLeft);
        game.play(middleLeft);

        assertThatGameFeedbackEquals(GameFeedback.playSuccessfull);
    }

    @Test
    public void playersAlternate() {
        game.play(bottomLeft);
        assertThatCurrentPlayerEqualsTo(Player.PlayerO);

        game.play(middleLeft);
        assertThatCurrentPlayerEqualsTo(Player.PlayerX);
    }

    @Test
    public void playersDoNotAlternateWhenPositionAlreadyTaken() {
        Position pos = bottomLeft;
        game.play(pos);
        game.play(pos);

        assertThatCurrentPlayerEqualsTo(Player.PlayerO);
    }

    @Test
    public void drawWhenAllAreFullAndNonOfThePlayersHas3InRow() {
        playMoves(topLeft, topMiddle, topRight, middleLeft, middleMiddle, bottomRight, middleRight, bottomLeft, bottomMiddle);

        assertThatGameFeedbackEquals(GameFeedback.gameEndedWithDraw);
    }

    @Test
    public void playerXWinsWhenTheTopRowContainsAllX() {
        playMoves(topLeft, middleLeft, topRight, middleRight, topMiddle);

        assertWinnerEqualsTo(Player.PlayerX);
    }

    @Test
    public void playerOWinsWhenTheMiddleRowContainsAllO() {
        playMoves(topLeft, middleLeft, topRight, middleRight, bottomMiddle, middleMiddle);

        assertWinnerEqualsTo(Player.PlayerO);
    }

    @Test
    public void playerXWinsWhenTheBottomRowContainsAllX() {
        playMoves(bottomLeft, middleLeft, bottomRight, middleRight, bottomMiddle);

        assertWinnerEqualsTo(Player.PlayerX);
    }

    @Test
    public void playerOWinsWhenTheLeftColumnContainsAllO() {
        playMoves(topMiddle, topLeft, topRight, middleLeft, bottomRight, bottomLeft);

        assertWinnerEqualsTo(Player.PlayerO);
    }

    @Test
    public void playerXWinsWhenTheMiddleColumnContainsAllX() {
        playMoves(topMiddle, topLeft, middleMiddle, middleLeft, bottomMiddle);

        assertWinnerEqualsTo(Player.PlayerX);
    }

    @Test
    public void playerXWinsWhenTheRightColumnContainsAllX() {
        playMoves(topRight, topLeft, middleRight, middleLeft, bottomRight);

        assertWinnerEqualsTo(Player.PlayerX);
    }

    @Test
    public void playerXWinsWhenSlashDownContainsAllX() {
        playMoves(topLeft, topRight, middleMiddle, topMiddle, bottomRight);

        assertWinnerEqualsTo(Player.PlayerX);
    }

    @Test
    public void playerXWinsWhenSlashUpContainsAllX() {
        playMoves(bottomLeft, bottomRight, middleMiddle, topMiddle, topRight);

        assertWinnerEqualsTo(Player.PlayerX);
    }

    private void assertThatGameFeedbackEquals(GameFeedback expectedFeedback) {
        assertThat(game.feedback(), equalTo(expectedFeedback));
    }

    private void assertThatCurrentPlayerEqualsTo(Player expectedPlayer) {
        assertThat(game.currentPlayer(), equalTo(expectedPlayer));
    }

    private void assertWinnerEqualsTo(Player expectedWinner) {
        assertThatGameFeedbackEquals(GameFeedback.gameEndedWithWin);
        assertThatCurrentPlayerEqualsTo(expectedWinner);
    }

    private void playMoves(Position... moves) {
        for (Position move : moves) {
            game.play(move);
        }
    }
}
