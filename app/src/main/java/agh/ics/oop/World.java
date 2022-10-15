package agh.ics.oop;

public class World {
    public static void main(String[] args)
    {
        //#region lab1
        System.out.println("system wystartowal");
        Run(CommandStringToDirectionArray(args));
        System.out.println("system zakonczyl dzialanie");
        //#endregion lab1

        //#region lab2
        Vector2D position1 = new Vector2D(1,2);
        Vector2D position2 = new Vector2D(-2, 1);
        System.out.println(position1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        System.out.println(MapDirection.NORTH.toString());
        System.out.println(MapDirection.NORTH.next());
        System.out.println(MapDirection.NORTH.previous());
        System.out.println(MapDirection.NORTH.toUnitVector());

    }

    static void Run(MoveDirection[] directions)
    {
        System.out.println("Zwierzak rozpoczyna ruch");
        for (MoveDirection direction : directions) {
            DirectionToMovementMessage(direction);
        }
    }

    static void DirectionToMovementMessage(MoveDirection toPrint)
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

    static MoveDirection CommandStringToDirection(String td)
    {
        MoveDirection retval = switch (td) {
            case "f" -> MoveDirection.FORWARD;
            case "b" -> MoveDirection.BACKWARD;
            case "l" -> MoveDirection.LEFT;
            case "r" -> MoveDirection.RIGHT;
            default -> null;
        };
        return retval;
    }

    static MoveDirection[] CommandStringToDirectionArray(String[] td)
    {
        MoveDirection[] retval = new MoveDirection[td.length];
        for(int i = 0; i < td.length; i++)
        {
            retval[i] = CommandStringToDirection(td[i]);
        }
        return retval;
    }
}
