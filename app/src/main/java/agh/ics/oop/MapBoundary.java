package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;


public class MapBoundary implements IPositionChangeObserver{

    private Comparator<Vector2d> XCOM = new Comparator<Vector2d>() {
        public int compare(Vector2d arg0, Vector2d arg1)
        {
            if(arg0 == arg1) return 0;
            if(arg0.x - arg1.x != 0) return arg0.x - arg1.x;
            if(arg0.y - arg1.y != 0) return arg0.y - arg1.y;
            //same position, animal vs grass.
            if(map.objectAt(arg0) instanceof Animal) return 1;
            return -1;
        }
    };

    private Comparator<Vector2d> YCOM = new Comparator<Vector2d>() {
        public int compare(Vector2d arg0, Vector2d arg1) {
            if(arg0 == arg1) return 0;
            if(arg0.y - arg1.y != 0) return arg0.y - arg1.y;
            if(arg0.x - arg1.x != 0) return arg0.x - arg1.x;
            //same position, animal vs grass.
            if(map.objectAt(arg0) instanceof Animal) return 1;
            return -1;
        }
    };


    SortedSet<Vector2d> ObjectsByX;
    SortedSet<Vector2d> ObjectsByY;
    AbstractWorldMap map;
    public MapBoundary(AbstractWorldMap map)
    {
        this.map = map;
        ObjectsByX = new TreeSet<Vector2d>(XCOM);
        ObjectsByY = new TreeSet<Vector2d>(YCOM);
    }

    public void place(Vector2d tp)
    {
        ObjectsByX.add(tp);
        ObjectsByY.add(tp);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        ObjectsByX.remove(oldPosition);
        ObjectsByY.remove(oldPosition);
        ObjectsByX.add(newPosition);
        ObjectsByY.add(newPosition);
    }
    
    public Vector2d lowerleft()
    {
        //return ObjectsByX.first().lowerLeft(ObjectsByY.first());
        Vector2d retval = ObjectsByX.first();
        for (Vector2d vector2d : ObjectsByX) {
            retval = retval.lowerLeft(vector2d);
        } return retval;
    }
    //public (Vector2d,Vector2d) boundary(){...} too hard 4 java, I have to have 2 methods for this stuff even though they literally always will be ran one after the other.
    public Vector2d upperright()
    {
        //return ObjectsByX.last().lowerLeft(ObjectsByY.last());
        Vector2d retval = ObjectsByX.first();
        for (Vector2d vector2d : ObjectsByX) {
            retval = retval.upperRight(vector2d);
        } return retval;
    }

        
}
