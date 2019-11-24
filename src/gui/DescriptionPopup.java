package gui;

import javafx.stage.Popup;
import javafx.scene.control.TextArea;

public class DescriptionPopup {
	
	private Popup popup;
	private TextArea textA;
	
	public DescriptionPopup (int x, int y, String text) {
        popup = new Popup();
        popup.setX(x);
        popup.setY(y);
        
		textA = new TextArea(text);
        popup.getContent().addAll(textA);
        textA.setMinWidth(80);
        textA.setMinHeight(50);
	}
	
	// getter and setter methods
	
	public Popup getPopup() {
		return popup;
	}
	
	public void setText(String text) {
		textA.setText(text);
	}
	
	public String getText() {
		return textA.getText();
	}
	
}
