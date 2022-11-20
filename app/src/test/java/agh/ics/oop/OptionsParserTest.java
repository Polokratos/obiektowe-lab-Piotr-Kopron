package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class OptionsParserTest {
    @Test
    public void parseTest()
    {
        String[] input = {"f","b"};
        MoveDirection[] expected = {MoveDirection.FORWARD,MoveDirection.BACKWARD};
        MoveDirection[] actual = OptionsParser.parse(input);
        assertEquals(expected[0],actual[0]);
        assertEquals(expected[1],actual[1]);
    }

    @Test
    public void parseFailTest()
    {
        String[] input = {"f","b","i like trains"};
        try {
            OptionsParser.parse(input);
            fail();
        } catch (IllegalArgumentException e) {
            //no-op
        }
    }
}
