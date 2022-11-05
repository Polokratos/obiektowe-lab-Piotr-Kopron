package agh.ics.oop;

import java.util.ArrayList;
import java.util.Random;

import java.lang.Math;

public class GrassField extends AbstractWorldMap{
    
    protected ArrayList<Grass> patches;
    

    public GrassField(int grassAmount)
    {
        vis = new MapVisualizer(this);
        patches = new ArrayList<>();
        animals = new ArrayList<>();
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
            patches.add(new Grass(pos));
        }
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if(animal.getPosition().equals(position))
                return animal;
        }//Animals get first priority.
        for (Grass grass : patches) {
            if(grass.getPosition().equals(position))
            return grass;
        }
        return null;
    }

    

    @Override
    public String toString() {
        //Getting the bounding box
        Vector2d ll;
        Vector2d ur;
        if(patches.isEmpty())
        {
            if(animals.isEmpty())
                return "map is empty";
            ll = animals.get(0).getPosition();
            ur = animals.get(0).getPosition();
        }
        else
        {
            ll = patches.get(0).getPosition();
            ur = patches.get(0).getPosition();
        }
        
        for (Grass patch : patches) {
            ll = ll.lowerLeft(patch.getPosition());
            ur = ur.upperRight(patch.getPosition());
        }
        for (Animal animal : animals) {
            ll = ll.lowerLeft(animal.getPosition());
            ur = ur.upperRight(animal.getPosition());
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
        int barrier = (int)Math.ceil(Math.sqrt(10*patches.size()));
        boolean stillIn = true;
        while (stillIn) {
            int x = rng.nextInt(barrier);
            int y = rng.nextInt(barrier);
            var vec = new Vector2d(x, y);
            stillIn = false;
            inner:
            for (Grass grass : patches) {
                if(grass.getPosition().equals(vec))
                stillIn = true;
                break inner; //no need to check further
            }
        }
    }

}
