package agh.ics.oop;

public class Animal {
    private MapDirection currentDirection;
    private Vector2d currentPosition;

    public Animal(MapDirection startingDirection, Vector2d startingPosition)
    {
        currentDirection = startingDirection;
        currentPosition = startingPosition;
    }
    public Animal() 
    {
        this(MapDirection.NORTH,new Vector2d(2, 2));
    }

    public String toString()
    {
        return "Position: " + currentPosition.toString() + " Direction: " + currentDirection.toString();
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
        currentPosition.add(movementVector);
        if(isoutofbounds())
            currentPosition.subtract(movementVector);
    }

    //encapsulate map size
    private boolean isoutofbounds()
    {
        return
        currentPosition.x >= 0 &&
        currentPosition.y >= 0 &&
        currentPosition.x <= 4 &&
        currentPosition.y <= 4;
    }
    
}
