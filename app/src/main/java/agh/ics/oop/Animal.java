package agh.ics.oop;

public class Animal {
    private MapDirection currentDirection;
    private Vector2d currentPosition;
    private IWorldMap map;
    //Depracated constructors without a map refernce.
    @Deprecated
    public Animal(MapDirection startingDirection, Vector2d startingPosition)
    {
        currentDirection = startingDirection;
        currentPosition = startingPosition;
    }
    @Deprecated
    public Animal() 
    {
        this(MapDirection.NORTH,new Vector2d(2, 2));
    }
    public Animal(IWorldMap map, Vector2d startingPosition)
    {
        this.map = map;
        this.currentDirection = MapDirection.NORTH; 
        //I love when the reference gives you magic values from nowhere!
        this.currentPosition = startingPosition;
    }
    public Animal(IWorldMap map)
    {
        this(map,new Vector2d(0, 0));   
        // I love it even more when the reference doesnt even give you the values of magic values...
    }
    public String toString()
    {
        return switch (currentDirection) {
            case NORTH -> "N";
            case WEST -> "W";
            case EAST -> "E";
            case SOUTH -> "S";
            default -> null;
        };
    }

    //Returns the position of the animal without allowing for modification
    Vector2d getPosition()
    {
        return new Vector2d(currentPosition.x, currentPosition.y);
    }

    public boolean isAt(Vector2d position)
    {
        return position.equals(currentPosition);
    }

    public void move(MoveDirection direction)
    {
        switch (direction) {
            case FORWARD:
                forward(1);
                break;
            case BACKWARD:
                forward(-1);
                break;
            case LEFT:
                currentDirection = currentDirection.previous();
                break;
            case RIGHT:
                currentDirection = currentDirection.next();
                break;
            default:
                break;
        }
    }

    //removes code duplication in moving forwards/backwards.
    private void forward(int delta)
    {
        int x = 0;
        int y = 0;
        switch (currentDirection) {
            case NORTH:
                y = delta;
                break;
            case EAST:
                x = delta;
                break;
            case SOUTH:
                y = -delta;
                break;
            case WEST:
                x = -delta;
                break;
            default:
                break;
        }
        Vector2d movementVector = new Vector2d(x, y);
        if(map.canMoveTo(currentPosition.add(movementVector)))
        {
            currentPosition = currentPosition.add(movementVector);   
        }
        
        
    }
}
