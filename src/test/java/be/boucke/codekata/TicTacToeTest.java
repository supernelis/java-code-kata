package be.boucke.codekata;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

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
        Position pos = Position.bottomLeft;
        game.play(pos);
        game.play(pos);

        assertThatGameFeedbackEquals(GameFeedback.positionAlreadytaken);
    }

    @Test
    public void playersCanPlayOnAnEmptyPosition() {
        game.play(Position.bottomLeft);
        game.play(Position.middleLeft);

        assertThatGameFeedbackEquals(GameFeedback.playSuccessfull);
    }

    @Test
    public void playersAlternate() {
        game.play(Position.bottomLeft);
        assertThatCurrentPlayerEqualsTo(Player.PlayerO);

        game.play(Position.middleLeft);
        assertThatCurrentPlayerEqualsTo(Player.PlayerX);
    }

    @Test
    public void playersDoNotAlternateWhenPositionAlreadyTaken() {
        Position pos = Position.bottomLeft;
        game.play(pos);
        game.play(pos);

        assertThatCurrentPlayerEqualsTo(Player.PlayerO);
    }

    @Test
    public void drawWhenAllAreFullAndNonOfThePlayersHas3InRow() {
        playMoves(Position.topLeft, Position.topMiddle, Position.topRight, Position.middleLeft, Position.middleMiddle, Position.bottomRight, Position.middleRight, Position.bottomLeft, Position.bottomMiddle);

        assertThatGameFeedbackEquals(GameFeedback.gameEndedWithDraw);
    }

    @Test
    public void playerXWinsWhenTheTopRowContainsAllX() {
        playMoves(Position.topLeft,Position.middleLeft, Position.topRight, Position.middleRight, Position.topMiddle);

        assertThatGameFeedbackEquals(GameFeedback.gameEndedWithWin);
        assertThatCurrentPlayerEqualsTo(Player.PlayerX);
    }

    @Test
    public void playerOWinsWhenTheMiddleRowContainsAllO() {
        playMoves(Position.topLeft,Position.middleLeft, Position.topRight, Position.middleRight, Position.bottomMiddle, Position.middleMiddle);

        assertThatGameFeedbackEquals(GameFeedback.gameEndedWithWin);
        assertThatCurrentPlayerEqualsTo(Player.PlayerO);
    }

    private void assertThatGameFeedbackEquals(GameFeedback expectedFeedback) {
        assertThat(game.feedback(), equalTo(expectedFeedback));
    }

    private void assertThatCurrentPlayerEqualsTo(Player expectedPlayer) {
        assertThat(game.currentPlayer(), equalTo(expectedPlayer));
    }

    private void playMoves(Position... moves) {
        for (Position move : moves) {
            game.play(move);
        }
    }
}
