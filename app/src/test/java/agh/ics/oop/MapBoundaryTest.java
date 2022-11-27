package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class MapBoundaryTest {
    @Test
    public void placeTest() {
        var bd = new MapBoundary(null); //map unnescesary.
        var v1 = new Vector2d(0, 0);
        var v2 = new Vector2d(0, 1);
        var v3 = new Vector2d(1, 0);
        var v4 = new Vector2d(1, 1);

        bd.place(v1);
        bd.place(v2);
        bd.place(v3);
        bd.place(v4);

        assertEquals(v1,bd.lowerleft());
        assertEquals(v4,bd.upperright());
    }

    @Test
    public void placeFailTest()
    {
        var bd = new MapBoundary(null);
        var v1 = new Vector2d(0, 0);
        var v2 = new Vector2d(0, 0);
        bd.place(v1);
        try {
            bd.place(v2);
            fail();    
        } catch (Exception e) {
            //no-op
        }
    }
}
