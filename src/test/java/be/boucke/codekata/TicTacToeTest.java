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
    public void xAlwaysGoesFirst(){
        assertThat(game.currentPlayer(),equalTo("PlayerX"));
    }

    @Test
    public void playersCannotPlayOnTheSamePosition() {
        Positions pos = Positions.bottomLeft;
        game.play(pos);
        game.play(pos);

        assertThat(game.feedback(),equalTo(GameFeedback.positionAlreadytaken));
    }

    @Test
    @Ignore
    public void currentPlayerDoesNotChangeWhenPlayingOnTheSamePosition() {
        Positions pos = Positions.bottomLeft;
        game.play(pos);
        game.play(pos);

        assertThat(game.currentPlayer(),equalTo("Player0"));

    }
}
