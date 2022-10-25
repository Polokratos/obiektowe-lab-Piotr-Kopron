package agh.ics.oop;

import org.junit.jupiter.api.Test;

public class RectangularMapTest {
    
    @Test
    public void FullIntergationTest()
    {
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f",};

        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        TestSimulationEngine engine = new TestSimulationEngine(directions, map, positions);
        
        Animal[] results = engine.runWithResults();
        Vector2d[] expectedAnimalPositions = {new Vector2d(3, 5), new Vector2d(2, 0)};
        assert(results.length == 2);
         //MapVisualizer doest differentiate between animals, therefore we neither.
         // We check if all positions match with all animals.
         //Under the assumption that a spot can be claimed only by one animal, this is enough.
        for (Animal animal : results) {
            boolean found = false;
            for (Vector2d vector2d : expectedAnimalPositions) {
                if(animal.getPosition().equals(vector2d))
                found = true;
            }
            assert(found);
        }
    }
}
