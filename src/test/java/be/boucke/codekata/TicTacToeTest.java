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
        game.play(Position.topLeft);
        game.play(Position.topMiddle);
        game.play(Position.topRight);
        game.play(Position.middleLeft);
        game.play(Position.middleMiddle);
        game.play(Position.bottomRight);
        game.play(Position.middleRight);
        game.play(Position.bottomLeft);
        game.play(Position.bottomMiddle);

        assertThatGameFeedbackEquals(GameFeedback.gameEndedWithDraw);
    }

    private void assertThatGameFeedbackEquals(GameFeedback expectedFeedback) {
        assertThat(game.feedback(), equalTo(expectedFeedback));
    }

    private void assertThatCurrentPlayerEqualsTo(Player expectedPlayer) {
        assertThat(game.currentPlayer(), equalTo(expectedPlayer));
    }
}
