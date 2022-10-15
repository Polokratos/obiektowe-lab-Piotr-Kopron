package agh.ics.oop;

public class Vector2D {
    public final int x;
    public final int y;

    public Vector2D(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public String toString()
    {
        return "(" + x + "," + y + ")";
    }

    //Returns true if THIS vector precedes OTHER vector
    public boolean precedes(Vector2D other)
    {
        return this.x <= other.x && this.y <= other.y;
    }

    //Returns true if THIS vector follows OTHER vector
    public boolean follows(Vector2D other)
    {
        return other.precedes(this);
    }

    public Vector2D upperRight(Vector2D other)
    {
        int X = this.x > other.x ? this.x : other.x;
        int Y = this.y > other.y ? this.y : other.y;
        return new Vector2D(X, Y);
    }

    public Vector2D lowerLeft(Vector2D other)
    {
        int X = this.x < other.x ? this.x : other.x;
        int Y = this.y < other.y ? this.y : other.y;
        return new Vector2D(X, Y);
    }

    public Vector2D add(Vector2D other)
    {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D subtract(Vector2D other)
    {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }
    public Vector2D opposite()
    {
        return new Vector2D(-this.x, -this.y);
    }

    public boolean equals(Object other){
        if (this == other)
          return true;
        if (!(other instanceof Vector2D))
          return false;
        Vector2D that = (Vector2D) other;
        return this.x == that.x && this.y == that.y;
      }
}
