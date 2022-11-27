package agh.ics.oop;

import javafx.scene.image.Image;

public abstract class AbstractMapElement {
    protected Vector2d currentPosition;
    public abstract Image getTexture();

    public Vector2d getPosition() {
        return new Vector2d(currentPosition.x, currentPosition.y);
    }
}
