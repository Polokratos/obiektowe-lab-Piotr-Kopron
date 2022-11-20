package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class IWorldMapTest {
    
    static IWorldMap subject1 = new RectangularMap(10, 10);
    static IWorldMap subject2 = new GrassField(10);
    static Animal meat11 = new Animal(subject1,new Vector2d(0, 0));
    static Animal meat12 = new Animal(subject1,new Vector2d(0, 1));
    static Animal meat13F = new Animal(subject1,new Vector2d(0, 0)); //F=> fail (should fail to place)
    static Animal meat21 = new Animal(subject2,new Vector2d(0, 0));
    static Animal meat22 = new Animal(subject2,new Vector2d(0, 1));
    static Animal meat23F = new Animal(subject1,new Vector2d(0, 0)); //F=> fail (should fail to place)
    //Test for placing animals, and coincidentally an initializer for all further tests.
    @BeforeAll
    @Test
    public static void INITPlaceGoodTest() {
        assert(subject1.place(meat11));
        assert(subject1.place(meat12));
        assert(subject2.place(meat21));
        assert(subject2.place(meat22));
    }

    @Test
    public void PlaceFailTest() {
        try {
            subject1.place(meat13F);
            fail();
        } catch (IllegalArgumentException e) {
            //no-op
        }
        try {
            subject2.place(meat23F);
            fail();
        } catch (IllegalArgumentException e) {
            //no-op
        }

    }

    @Test
    public void canMoveTest() {
        var good = new Vector2d(1, 1);
        var bad = new Vector2d(0, 0);
        assert(subject1.canMoveTo(good));
        assert(!subject1.canMoveTo(bad));
        assert(subject2.canMoveTo(good));
        assert(!subject2.canMoveTo(bad));
        //subject1 is a rectangular, extra out of bounds check is required.
        assert(!subject1.canMoveTo(new Vector2d(-1, -1)));
    }

    @Test
    public void isOccupiedTest() {
        var bad = new Vector2d(1, 1);
        var good = new Vector2d(0, 0);
        assert(subject1.isOccupied(good));
        assert(!subject1.isOccupied(bad));
        assert(subject2.isOccupied(good));
        assert(!subject2.isOccupied(bad));
    }

    @Test
    public void objectAtTest() {
        var empty = new Vector2d(3, 3);
        var a1 = new Vector2d(0, 0);
        var a2 = new Vector2d(0, 1);

        assert(subject1.objectAt(empty) == null);
        assert(subject2.objectAt(empty) == null);
        assert(subject1.objectAt(a1).equals(meat11));
        assert(subject2.objectAt(a1).equals(meat21));
        assert(subject1.objectAt(a2).equals(meat12));
        assert(subject2.objectAt(a2).equals(meat22));
    }

}
