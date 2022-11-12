package agh.ics.oop;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.lang.Math;

public class GrassField extends AbstractWorldMap{
    
    protected HashMap<Vector2d,Grass> patches;
    

    public GrassField(int grassAmount)
    {
        vis = new MapVisualizer(this);
        patches = new HashMap<>();
        animals = new HashMap<>();
        Random rng = new Random();
        int barrier = (int)Math.ceil(Math.sqrt(10*grassAmount));
        ArrayList<Vector2d> patch_notes = new ArrayList<>();
        for (int i = 0; i < grassAmount; i++) {
            Vector2d pos = new Vector2d(rng.nextInt(barrier),rng.nextInt(barrier));
            while(patch_notes.contains(pos)) { //do while not used to have pos outside of the loop.
                pos = new Vector2d(rng.nextInt(barrier),rng.nextInt(barrier));
            }
            patch_notes.add(pos);
        }
        for (Vector2d pos : patch_notes) {
            patches.put(pos,new Grass(pos));
        }
    }

    @Override
    public Object objectAt(Vector2d position) {
        if(animals.containsKey(position))
            return animals.get(position);
        if(patches.containsKey(position))
            return patches.get(position);
        return null;
    }

    

    @Override
    public String toString() {
        Set<Vector2d> akeys = animals.keySet();
        Set<Vector2d> gkeys = patches.keySet();
        Vector2d ll,ur;
        if(akeys.size() > 0)
        {
            ll = akeys.iterator().next();
            ur = akeys.iterator().next();
        }
        else if(gkeys.size() > 0)
        {
            ll = gkeys.iterator().next();
            ur = gkeys.iterator().next();
        }
        else return "Map is empty";
        for (Vector2d vector2d : akeys) {
            ll = ll.lowerLeft(vector2d);
            ur = ur.upperRight(vector2d);
        }
        for (Vector2d vector2d : gkeys) {
            ll = ll.lowerLeft(vector2d);
            ur = ur.upperRight(vector2d);
        }
        return vis.draw(ll,ur);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(objectAt(position) == null) return true;
        if(objectAt(position) instanceof Animal) return false;
        //VEERY hacky, far fetter to handle this via Observer. 
        //But of course, Observer is the next labs! Did I guess right? :)
        if(objectAt(position) instanceof Grass)
        {
            MoveGrass(position);
            return true;
        }
        return true;
    }
    
    private void MoveGrass(Vector2d position) {
        Random rng = new Random();
        int barrier = (int)Math.ceil(Math.sqrt(10*patches.size()))+1;
        Grass toMove = (Grass) objectAt(position); //should always be grass.
        Vector2d positionOLD = toMove.getPosition();
        Vector2d pos;
        do {
            pos = new Vector2d(rng.nextInt(barrier),rng.nextInt(barrier));    
            toMove.setPosition(pos);
        } while (objectAt(pos) != null);
        patches.remove(positionOLD);
        patches.put(pos, toMove);
    }

}
