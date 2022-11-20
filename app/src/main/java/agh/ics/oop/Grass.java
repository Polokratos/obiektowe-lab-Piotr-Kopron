package agh.ics.oop;

public class Grass {
    private Vector2d currentPosition;
    public Grass(Vector2d pos)
    {
        currentPosition = pos;
    }

    public Vector2d getPosition() {
        return new Vector2d(currentPosition.x, currentPosition.y);
    }

    public void setPosition(Vector2d value)
    {
        currentPosition = value;
    }

    public String toString() {
        return "*";
    }
}
