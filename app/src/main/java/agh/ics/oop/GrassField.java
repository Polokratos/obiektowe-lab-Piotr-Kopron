package agh.ics.oop;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

import java.lang.Math;

public class GrassField extends AbstractWorldMap{
    
    protected HashMap<Vector2d,Grass> patches;
    
    protected MapBoundary boundary;

    //Dirty flag: these vectors were subject to change and this change was not reflected in GUI (yet).
    public ArrayList<Vector2d> dirtyVectors;
    //Extensive changes to te map to the point of redrawing it all.
    public boolean superDirty;

    public GrassField(int grassAmount)
    {
        vis = new MapVisualizer(this);
        boundary = new MapBoundary(this);
        patches = new HashMap<>();
        animals = new HashMap<>();
        dirtyVectors = new ArrayList<>();
        superDirty = true; //At simulation start, the map needs to be drawn.
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
            boundary.place(pos);
        }
    }

    @Override
    public AbstractMapElement objectAt(Vector2d position) {

        if(animals.containsKey(position))
            return animals.get(position);
        if(patches.containsKey(position))
            return patches.get(position);
        return null;
    }

    @Override
    public boolean place(Animal animal) {
        super.place(animal);
        dirtyVectors.add(animal.getPosition());
        Vector2d ll = boundaryLowerLeft();
        Vector2d ur = boundaryLowerLeft();
        boundary.place(animal.getPosition());
        if(!ll.equals(boundaryLowerLeft()) || !ur.equals(boundaryUpperRight())) //if boundary changed after placing
            superDirty = true; //redraw.
        animal.addObserver(this);
        return true;
    }

    @Override
    public String toString() {
        if(animals.keySet().size() == 0 && patches.keySet().size() == 0)
            return "Map is empty";
        return vis.draw(boundaryLowerLeft(),boundaryUpperRight());
    }

    //y u no tuple
    public Vector2d boundaryLowerLeft(){return boundary.lowerleft();}
    public Vector2d boundaryUpperRight(){return boundary.upperright();}

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

    @Override
    public boolean isOccupied(Vector2d position) {
        if(!super.isOccupied(position)) return false;
        return (objectAt(position) instanceof Animal);
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
        dirtyVectors.add(pos);
        dirtyVectors.add(positionOLD);
        Vector2d ll = boundaryLowerLeft();
        Vector2d ur = boundaryUpperRight();
        boundary.positionChanged(positionOLD, pos);
        if(!ll.equals(boundaryLowerLeft()) || !ur.equals(boundaryUpperRight())) //if boundary changed after placing
            superDirty = true; //redraw.
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Vector2d ll = boundaryLowerLeft();
        Vector2d ur = boundaryUpperRight();
        dirtyVectors.add(oldPosition);
        dirtyVectors.add(newPosition);
        Animal toMove = animals.remove(oldPosition);
        animals.put(newPosition,toMove);
        boundary.positionChanged(oldPosition, newPosition);
        if(!ll.equals(boundaryLowerLeft()) || !ur.equals(boundaryUpperRight())) //if boundary changed after placing
            superDirty = true; //redraw.
    }

}
