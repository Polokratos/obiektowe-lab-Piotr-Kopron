package agh.ics.oop;

public class SimulationEngine implements IEngine{
    
    protected IWorldMap map;
    protected MoveDirection[] moves;
    protected Animal[] animals;
    public boolean initializedCorrectly;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] startingPositions)
    {
        this.map = map;
        this.moves = moves;
        this.animals = new Animal[startingPositions.length];
        initializedCorrectly = true;
        System.out.println(initializedCorrectly);
        for (int i = 0; i < startingPositions.length; i++) {
            animals[i] = new Animal(map,startingPositions[i]);
            initializedCorrectly &= map.place(animals[i]);
        }
        
    }
    public void run()
    {
        for (int i = 0; i < moves.length; i++) {
            animals[i%animals.length].move(moves[i]);
            System.out.println(map.toString());
        }
    }


}
