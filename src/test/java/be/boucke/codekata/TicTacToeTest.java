package be.boucke.codekata;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class TicTacToeTest {

    @Test
    public void xAlwaysGoesFirst(){
        TicTacToe game = new TicTacToe();
        String playerX = "PlayerX";

        assertThat(game.currentPlayer(),equalTo(playerX));
    }



}
