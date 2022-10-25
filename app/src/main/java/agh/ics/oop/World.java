package agh.ics.oop;

public class World {
    public static void main(String[] args)
    {   
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

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
