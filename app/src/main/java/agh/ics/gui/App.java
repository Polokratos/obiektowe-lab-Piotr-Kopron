package agh.ics.gui;

import agh.ics.oop.*;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    
    private GrassField map;
    private GridPane grid;
    private Button startButton;
    private TextField input;
    private HBox UITopRoot;
    private SimulationEngine engine;
    //Note that this representation does NOT contain the coordinate labels.
    private GUIElementBox[][] mapInGrid;

    @Override
    public void init() throws Exception {
        this.map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        engine = new SimulationEngine(map, positions,3000,this); //300 is magic number not given by assignment.
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        //Parent box for top- UI and map.
        
        startButton = new Button("Start!");
        startButton.setOnAction(e -> startSimulation());
        input = new TextField();
        UITopRoot = new HBox(startButton,input);
        //parent grid to all map labels
        grid = new GridPane();
        grid.setGridLinesVisible(true);
        Update();
        VBox root = new VBox(UITopRoot, grid);
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    public Vector2d ConvertCoordinatesGridToMap(Vector2d toConvert)
    {
        int mapx = map.boundaryLowerLeft().x+toConvert.x-1;
        int mapy = map.boundaryUpperRight().y-toConvert.y+1;
        return new Vector2d(mapx, mapy);
    }

    public Vector2d ConvertCoordinatesMapToGrid(Vector2d toConvert)
    {
        int gridx = toConvert.x+1;
        int gridy = map.boundaryUpperRight().y + 1-toConvert.y;
        return new Vector2d(gridx, gridy);
    }

    public void Update()
    {   
        if(map.superDirty) //only on major changes requiring a redraw anyway
        {
            grid.getChildren().clear();
            drawCoordinateLabels();    
            DrawMapInGrid();
            FormatGrid();
            map.superDirty = false;
        }
        else
        {
            for (Vector2d dirtyMapVector : map.dirtyVectors) {
                Vector2d dirtyMapInGridVector = 
                ConvertCoordinatesMapToGrid(dirtyMapVector).subtract(new Vector2d(1, 1));
                mapInGrid[dirtyMapInGridVector.x][dirtyMapInGridVector.y].Update(map.objectAt(dirtyMapVector));
            }
        }
        map.dirtyVectors.clear();
    }
    private void drawCoordinateLabels()
    {
        Vector2d ll = map.boundaryLowerLeft();
        Vector2d ur = map.boundaryUpperRight();
        //X-> col. Y-> row.
        //x==y==0 => x"y/x"

        grid.add(new Label("y/x"),0,0);

        //y==0,x!=0 => x coords
        for (int x = 1; x <x_amt(); x++) {
            String coord = ((Integer)(ll.x+x-1)).toString(); //ll.x -> smallest x. + iteration variable-1, since we start from 1.
            grid.add(new Label(coord),x,0);
        }
        //x==0, y!= 0 => y coords
        for (int y = 1; y < y_amt(); y++) {
            String coord = ((Integer)(ur.y-y+1)).toString(); //y vals must decrease when going downwards.
            grid.add(new Label(coord), 0, y);
        }
    }
    private void FormatGrid()
    {
        grid.setGridLinesVisible(true);
        int width = 40; //magic numbers the assignment doest tell you the values of, so I have to invent my own... AGAIN!
        int height = 40;

        for (int y = 0; y < y_amt(); y++) {
            grid.getRowConstraints().add(new RowConstraints(height));
        }
        for (int x = 0; x < x_amt(); x++) {
            grid.getColumnConstraints().add(new ColumnConstraints(width));
        }
        for (Node node : grid.getChildren()) {
            GridPane.setHalignment(node, HPos.CENTER);
        }
        
    }
    private void DrawMapInGrid()
    {
        mapInGrid = new GUIElementBox[x_amt()][y_amt()];
        //x*y != 0 => map objects.
        for (int x = 1; x < x_amt(); x++) { 
            for (int y = 1; y < y_amt(); y++) {
                
                //Grid coords -> map coords conversion.
                //int mapx = ll.x+x-1;
                //int mapy = ur.y-y+1;
                Vector2d mapcoords = ConvertCoordinatesGridToMap(new Vector2d(x, y));
                AbstractMapElement mapObject = map.objectAt(mapcoords);
                mapInGrid[x-1][y-1] = new GUIElementBox(mapObject);
                grid.add(mapInGrid[x-1][y-1], x, y);
            }
        }
    }

    //map boundary span (if ll = ur, then still one square to show, and one square for coordinates, so +2 for both x and)
    private int x_amt(){return map.boundaryUpperRight().x - map.boundaryLowerLeft().x + 2;}
    private int y_amt(){return map.boundaryUpperRight().y - map.boundaryLowerLeft().y + 2;}

    private void startSimulation()
    {
        try {
            MoveDirection[] moves = OptionsParser.parse(input.getText().split(","));
            engine.setMovement(moves);
            Thread simulation = new Thread(engine);
            simulation.start();
        } catch (IllegalArgumentException e) {
            //no-op; just fail to start the thread if user put in garbage.
        }
    }
}
