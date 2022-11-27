package agh.ics.oop;

import java.io.FileInputStream;

import javafx.scene.image.Image;

public class Grass extends AbstractMapElement {
    
    //static texture preload.
    private static Image loadImage()
    {
        try {
            return new Image(new FileInputStream("app\\src\\main\\resources\\grass.bmp"));
        } catch (Exception e) {
            return null;
        }
    }
    static Image texture = loadImage();
    
    @Override
    public Image getTexture() {
        return texture;
    }
    
    
    public Grass(Vector2d pos)
    {
        currentPosition = pos;
    }

    public void setPosition(Vector2d value)
    {
        currentPosition = value;
    }

    public String toString() {
        return "*";
    }
}
