package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap extends AbstractWorldMap{
    //I hate the fact that this is the only solution that is within the spec.
    //Like, why do I have to iterate over all animals just to check if one of them is in the spot?
    //Why can't I hold info about all the spots and instead of asking all the animals, just ask the spot?
    //Efficiency, people! O(n) vs O(1)...
    //But no, I can't have a .moveAnimal(Animal, Vecotr2d from, Vector2d to).
    //Even .place(Animal) being .place(Animal, Vector2d where) would be enough.
    //But as of right now, not storing animal position data on the map... WHYYY???
    private int width;
    private int height;
    

    public RectangularMap(int width, int height)
    {
        this.width = width;
        this.height = height;
        vis = new MapVisualizer(this);
        animals = new ArrayList<>();
    }

    @Override
    public boolean canMoveTo(Vector2d position)
    {
        if(position.x < 0 || position.y < 0 || position.x > width || position.y > height)
            return false;
        if(isOccupied(position))
            return false;
        return true;
    }
    @Override
    public Object objectAt(Vector2d position)
    {
        
        for (var animal : animals) {
            if(animal.getPosition().equals(position))
            return animal;
        }
        return null;
    }
    
    @Override
    public String toString()
    {
        var empty = new Vector2d(0,0);
        var dim = new Vector2d(width, height);
        return vis.draw(empty.lowerLeft(dim),empty.upperRight(dim));
    }

}
