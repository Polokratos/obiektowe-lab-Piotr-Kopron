package agh.ics.oop;

public enum MapDirection {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    public String toString()
    {
        switch (this) {
            case NORTH:
                return "Polnoc";
            case SOUTH:
                return "Poludnie";
            case EAST:
                return "Wschod";
            case WEST:
                return "Zachod";
            default:
                return "null";
        }
    }
    public MapDirection next()
    {
        switch (this) {
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
            default:
                return null; //null -> null
        }
    }

    public MapDirection previous()
    {
        return next().next().next();
    }

    public Vector2d toUnitVector()
    {
        switch (this) {
            case NORTH:
                return new Vector2d(0, 1);
            case EAST:
                return new Vector2d(1, 0);
            case SOUTH:
                return new Vector2d(0, -1);
            case WEST:
                return new Vector2d(-1, 0);
            default:
                return null; //null -> null
        }
    }
}
