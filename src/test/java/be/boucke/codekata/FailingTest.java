package be.boucke.codekata;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class FailingTest {

    @Test
    public void failingTest(){
        assertThat(true,equalTo(true));
    }
}
