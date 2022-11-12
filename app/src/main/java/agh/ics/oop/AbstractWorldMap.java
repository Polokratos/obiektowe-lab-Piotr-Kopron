package agh.ics.oop;

import java.util.HashMap;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    
    protected HashMap<Vector2d,Animal> animals;
    protected MapVisualizer vis;

    @Override
    public boolean isOccupied(Vector2d position)
    {
        return !(objectAt(position) == null);
    }

    @Override
    public boolean place(Animal animal) {
        if(!canMoveTo(animal.getPosition()))
            return false;
        animals.put(animal.getPosition(), animal);
        return true;
    }

    //Unknown dimentions, unknown animal => element placement... 
    //This useless stub is the best I can think of.
    @Override
    public String toString() {
        if(animals.isEmpty())
        return "map is empty";
        //Getting the bounding box
        Vector2d ll = new Vector2d(0, 0); //magic number, but such is the joy of not having a hook to animals
        Vector2d ur = new Vector2d(0, 0); //magic number, but such is the joy of not having a hook to animals
        for (Animal animal : animals.values()) {
            ll = ll.lowerLeft(animal.getPosition());
            ur = ur.upperRight(animal.getPosition());
        }

        return vis.draw(ll,ur);
    }

    @Override //we just assume it's an animal that moves, i guess.
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        animals.put(newPosition,animals.remove(oldPosition));
    }



}
