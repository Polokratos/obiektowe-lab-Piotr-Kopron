package agh.ics.oop;
import java.util.ArrayList;

public class OptionsParser {
    

    /**
     * @throws IllegalArgumentException
     * Upon entering an invalid argument
     * 
     */
    public static MoveDirection[] parse(String[] input)
    {
        ArrayList<MoveDirection> list = new ArrayList<>(); //we don't know how many commands are valid 
        for (String i : input) {
            MoveDirection md = parseCommand(i); //throws IllegalArumentException, we just rethrow.
            if(md != null)
                list.add(md);
        }
        MoveDirection[] retval = new MoveDirection[list.size()];
        retval = list.toArray(retval);
        return retval;
    }

    
    /**
     * @summary
     * decoupling singular command from grouping code.
     * @throws IllegalArgumentException
     * Upon entering an invalid argument
     * 
     */
    private static MoveDirection parseCommand(String input)
    {
        MoveDirection retval = switch (input) {
            case "f" -> MoveDirection.FORWARD;
            case "forward" -> MoveDirection.FORWARD;
            case "b" -> MoveDirection.BACKWARD;
            case "backward" -> MoveDirection.BACKWARD;
            case "l" -> MoveDirection.LEFT;
            case "left" -> MoveDirection.LEFT;
            case "r" -> MoveDirection.RIGHT;
            case "right" -> MoveDirection.RIGHT;
            default -> throw new IllegalArgumentException("Argument" + input + "is not legal move specification");
        };
        return retval;

    }
}
