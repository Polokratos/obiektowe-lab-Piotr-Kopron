package agh.ics.oop;

import agh.ics.gui.App;
import javafx.application.Platform;

public class SimulationEngine implements IEngine, Runnable{
    
    protected IWorldMap map;
    protected MoveDirection[] moves;
    protected Animal[] animals;
    public boolean initializedCorrectly;
    protected int moveDelay;
    protected App app;
    
    public void setMovement(MoveDirection[] args)
    {
        moves = args;
    }

    public SimulationEngine(IWorldMap map, Vector2d[] startingPositions, int moveDelay, App app)
    {
        this.map = map;
        
        this.animals = new Animal[startingPositions.length];
        this.app = app;
        this.moveDelay = moveDelay;
        initializedCorrectly = true;
        System.out.println(initializedCorrectly);
        for (int i = 0; i < startingPositions.length; i++) {
            animals[i] = new Animal(map,startingPositions[i]);
            initializedCorrectly &= map.place(animals[i]);
        }
    }
    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] startingPositions, int moveDelay, App app)
    {
        this(map,startingPositions,moveDelay,app);
        this.moves = moves;
    }
    public void run()
    {
        for (int i = 0; i < moves.length; i++) {
            animals[i%animals.length].move(moves[i]);
            System.out.println(map.toString());
            try {
                Thread.sleep(moveDelay);    
            } catch (InterruptedException e) {
                System.err.println("Simulation thread stopped");
            }
            Platform.runLater(()->app.Update());
        }
        System.out.println("Simulation tread finished");
    }


}
