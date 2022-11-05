package agh.ics.oop;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public String toString()
    {
        return "(" + x + "," + y + ")";
    }

    //Returns true if THIS vector precedes OTHER vector
    public boolean precedes(Vector2d other)
    {
        return this.x <= other.x && this.y <= other.y;
    }

    //Returns true if THIS vector follows OTHER vector
    public boolean follows(Vector2d other)
    {
        return other.precedes(this);
    }

    public Vector2d upperRight(Vector2d other)
    {
        int X = this.x > other.x ? this.x : other.x;
        int Y = this.y > other.y ? this.y : other.y;
        return new Vector2d(X, Y);
    }

    public Vector2d lowerLeft(Vector2d other)
    {
        int X = this.x < other.x ? this.x : other.x;
        int Y = this.y < other.y ? this.y : other.y;
        return new Vector2d(X, Y);
    }

    public Vector2d add(Vector2d other)
    {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d subtract(Vector2d other)
    {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }
    public Vector2d opposite()
    {
        return new Vector2d(-this.x, -this.y);
    }

    public boolean equals(Object other){
        if (this == other)
          return true;
        if (!(other instanceof Vector2d))
          return false;
        Vector2d that = (Vector2d) other;
        return this.x == that.x && this.y == that.y;
      }
}
