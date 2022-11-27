package agh.ics.gui;


import agh.ics.oop.AbstractMapElement;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class GUIElementBox extends VBox{
    
    public GUIElementBox(AbstractMapElement element)
    {
        //I'd like to put texture and label inside super() call, but I lose the cache. 
        // Can't have my cake and eat it too.
        super();
        if(element == null)
        {
            return;
        }
        Update(element);
    }

    //Change the element this box is representing.
    public void Update(AbstractMapElement element)
    {
        this.getChildren().clear();
        if(element == null) return;
        ImageView texture = new ImageView(element.getTexture());
        texture.setFitHeight(20);
        texture.setFitWidth(20);
        Label positionLabel = new Label(element.getPosition().toString());
        this.getChildren().addAll(texture,positionLabel);
        this.setCenterShape(true);
    }
}
