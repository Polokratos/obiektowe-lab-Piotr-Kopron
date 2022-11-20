package agh.ics.oop;

import agh.ics.gui.App;
import javafx.application.Application;

public class World {
    public static void main(String[] args)
    {   
        try {
            Application.launch(App.class, args);   
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("Unknown exception: " + e.getMessage());
        }
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
