package agh.ics.oop;

public class TestSimulationEngine extends SimulationEngine {
    public TestSimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] startingPositions)
    {
        super(moves, map, startingPositions,0,null);
    }

    public Animal[] runWithResults()
    {
        super.run();
        return animals;
    }
}
