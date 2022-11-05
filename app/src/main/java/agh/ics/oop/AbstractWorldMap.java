package agh.ics.oop;

import java.util.ArrayList;

public abstract class AbstractWorldMap implements IWorldMap {
    
    protected ArrayList<Animal> animals;
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
        animals.add(animal);
        return true;
    }

    //Unknown dimentions, unknown animal => element placement... this useless stub is the best I can think of.
    @Override
    public String toString() {
        if(animals.isEmpty())
        return "map is empty";
        //Getting the bounding box
        Vector2d ll = animals.get(0).getPosition();
        Vector2d ur = animals.get(0).getPosition();
        for (Animal animal : animals) {
            ll = ll.lowerLeft(animal.getPosition());
            ur = ur.upperRight(animal.getPosition());
        }

        return vis.draw(ll,ur);
    }
}
