package gui;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ChamberView extends GridPane {
    private String map;
    private int length;
    private int width;


    public ChamberView(int len, int wid){
       map = "/res/DungeonLayoutTemplate.png";
       length = len;
       width = wid;

    }


    public Node floorFactory(String img) {
        Image map = new Image(getClass().getResourceAsStream(img));
        Label toReturn = new Label();
        ImageView imageView = new ImageView(map);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        toReturn.setGraphic(imageView);
        return toReturn;
    }


}
