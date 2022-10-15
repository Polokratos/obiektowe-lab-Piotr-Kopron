package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class Vector2DTest {
    
    public Vector2D subject1 = new Vector2D(-1, 2);
    public Vector2D subject2 = new Vector2D(1,3);

    @Test
    public void equalsTest()
    {
        assertEquals(new Vector2D(1,3), subject2);
        assertFalse(subject1.equals(subject2));
        assert(subject1.equals(subject1));
    }

    @Test
    public void toStringTest()
    {
        assert(subject1.toString().equals("(-1,2)"));
    }
    
    @Test
    public void precedesTest()
    {
        assert(subject1.precedes(subject2));
    }
    
    @Test
    public void followsTest()
    {
        assert(subject2.follows(subject1));
    }

    @Test
    public void upperRightTest()
    {
        assertEquals(subject2, subject1.upperRight(subject2));
    }

    @Test
    public void lowerLeftTest()
    {
        assertEquals(subject1, subject1.lowerLeft(subject2));
    }

    @Test
    public void addTest()
    {
        Vector2D sum = new Vector2D(0,5);
        assertEquals(sum, subject1.add(subject2));
    }

    @Test
    public void subtractTest()
    {
        Vector2D diff = new Vector2D(-2, -1);
        assertEquals(diff, subject1.subtract(subject2));
    }

    public void oppositeTest()
    {
        Vector2D opp = new Vector2D(1, -2);
        assertEquals(opp, subject1.opposite());
    }


}
