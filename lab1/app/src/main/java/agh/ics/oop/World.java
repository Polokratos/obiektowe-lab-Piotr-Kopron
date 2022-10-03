package agh.ics.oop;

public class World {
    public static void main(String[] args)
    {
        System.out.println("system wystartowal");
        Run(CommandStringToDirectionArray(args));
        System.out.println("system zakonczyl dzialanie");
    }

    static void Run(Direction[] directions)
    {
        System.out.println("Zwierzak rozpoczyna ruch");
        for (Direction direction : directions) {
            DirectionToMovementMessage(direction);
        }
    }

    static void DirectionToMovementMessage(Direction toPrint)
    {
        String printval = "Zwierzak prousza sie: ";
        printval += switch (toPrint) {
            case FORWARD -> "do przodu";
            case BACKWARD -> "do tylu";
            case LEFT -> "w lewo";
            case RIGHT -> "w prawo";
            default -> "donikad";
        };
        System.out.println(printval);

    }

    static Direction CommandStringToDirection(String td)
    {
        Direction retval = switch (td) {
            case "f" -> Direction.FORWARD;
            case "b" -> Direction.BACKWARD;
            case "l" -> Direction.LEFT;
            case "r" -> Direction.RIGHT;
            default -> null;
        };
        return retval;
    }

    static Direction[] CommandStringToDirectionArray(String[] td)
    {
        Direction[] retval = new Direction[td.length];
        for(int i = 0; i < td.length; i++)
        {
            retval[i] = CommandStringToDirection(td[i]);
        }
        return retval;
    }
}
