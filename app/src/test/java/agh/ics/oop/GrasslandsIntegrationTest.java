package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

public class GrasslandsIntegrationTest {
    
    private class GrassFieldTESTCLASS extends GrassField
    {
        public GrassFieldTESTCLASS(int grassAmount)
        {
            super(grassAmount);
        }
        public HashMap<Vector2d,Animal> GetAnimals(){return animals;}
        public HashMap<Vector2d,Grass> GetGrass(){return patches;}
    }

    @Test
    public void GITest() {
        String[] args = {"f","b","r","l","f","f","r","r","f","f","f","f","f","f","f","f"};

        MoveDirection[] directions = OptionsParser.parse(args);
        GrassFieldTESTCLASS map = new GrassFieldTESTCLASS(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        assertEquals(map.GetAnimals().size(),2);
        assertEquals(map.GetGrass().size(),10);
        engine.run();
    }
}
