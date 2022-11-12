package agh.ics.oop;

public class Grass {
    private Vector2d position;
    public Grass(Vector2d pos)
    {
        position = pos;
    }

    public Vector2d getPosition()
    {
        return position;
    }

    public void setPosition(Vector2d value)
    {
        position = value;
    }

    public String toString() {
        return "*";
    }
}
