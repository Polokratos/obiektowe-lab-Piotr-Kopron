package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * As of blocking two animals in the same place:
 * Either hold an array of all animals and whenever one moves,
 * check if all other animals are NOT in our destination.
 * OR 
 * Add an Occupier field to {@Vector2d} to hold a reference to what animal occupies this tile,
 * and redesign our map so that you can't make two equal (in value, but not reference) vectors. 
 * This would be done via a VectorFactory, returning already existing vectors if already instantiated.
 * 
 * The first approach is better if number of animals is small relative to the map.
 * The second approach is otherwise
 */

public class AnimalTest {
    @Deprecated @Test
    public void NoMovementTest()
    {
        var a1 = new Animal();
        var a2 = new Animal(MapDirection.SOUTH, new Vector2d(0, 0));

        //toString assertions
        assertEquals("Position: (2,2) Direction: Polnoc", a1.toString());
        assertEquals("Position: (0,0) Direction: Poludnie", a2.toString());

        //isAt assertions
        assert(a1.isAt(new Vector2d(2, 2)));
        assert(a2.isAt(new Vector2d(0, 0)));
        
    }

    @Deprecated @Test
    public void SimpleMovementTest()
    {
        var a = new Animal();

        a.move(MoveDirection.FORWARD);
        assert(a.isAt( new Vector2d(2,3)));
        a.move(MoveDirection.BACKWARD);
        a.move(MoveDirection.BACKWARD);
        assert(a.isAt(new Vector2d(2, 1)));
    }

    @Deprecated @Test
    public void SimpleRotationTest()
    {
        var a = new Animal();

        a.move(MoveDirection.LEFT);
        assertEquals("Position: (2,2) Direction: Zachod", a.toString());
        a.move(MoveDirection.RIGHT);
        a.move(MoveDirection.RIGHT);
        assertEquals("Position: (2,2) Direction: Wschod", a.toString());
    }

    @Deprecated @Test
    public void OutOfBoundsTest()
    {
        var a = new Animal(MapDirection.NORTH,new Vector2d(0, 5));

        a.move(MoveDirection.FORWARD);
        assert(a.isAt(new Vector2d(0, 5)));
    }

    @Deprecated @Test
    public void ComplexMovementTest()
    {
        var a = new Animal();

        a.move(MoveDirection.FORWARD); // 2 3 N
        a.move(MoveDirection.LEFT); // 2 3 W
        a.move(MoveDirection.BACKWARD); //3 3 W
        a.move(MoveDirection.BACKWARD); //4 3 W
        a.move(MoveDirection.RIGHT); // 4 3 N
        a.move(MoveDirection.FORWARD); // 4 4 N

        assert(a.isAt(new Vector2d(4, 4)));
    }


    @Deprecated @Test public void ParsedCommandTest()
    {
        var a = new Animal();
        String[] cmdstr = {"f","l","l","x","b","I like trains","backward"};
        MoveDirection[] cmd = OptionsParser.parse(cmdstr);
        for (MoveDirection moveDirection : cmd) {
            a.move(moveDirection);
        }

        assert(a.isAt(new Vector2d(0, 0)));
    }
}
