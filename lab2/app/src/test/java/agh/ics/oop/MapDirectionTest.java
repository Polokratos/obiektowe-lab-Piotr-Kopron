package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MapDirectionTest {
    
    @Test
    public void nextTest()
    {
        assertEquals(MapDirection.NORTH,MapDirection.WEST.next());
        assertEquals(MapDirection.WEST,MapDirection.SOUTH.next());
        assertEquals(MapDirection.SOUTH,MapDirection.EAST.next());
        assertEquals(MapDirection.EAST,MapDirection.NORTH.next());
    }

    @Test
    public void previousTest()
    {
        assertEquals(MapDirection.NORTH,MapDirection.EAST.previous());
        assertEquals(MapDirection.WEST,MapDirection.NORTH.previous());
        assertEquals(MapDirection.SOUTH,MapDirection.WEST.previous());
        assertEquals(MapDirection.EAST,MapDirection.SOUTH.previous());
    }

    
}
