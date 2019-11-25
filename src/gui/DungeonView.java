package gui;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;

public class DungeonView extends GridPane {
    private String map;
    private int length;
    private int width;
	private Popup popup;

    public DungeonView(int len, int wid){
		map = "/res/DungeonLayoutTemplate.png";
		length = len;
		width = wid;
		popup = new Popup();

    }
	
	public Popup getPopup() {
		return popup;
	}
	

}
