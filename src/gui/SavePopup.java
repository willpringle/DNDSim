package gui;

import javafx.stage.Popup;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import java.io.File;

public class SavePopup {
	
	private Popup popup; // the popup 
	private TextArea textA;
	private FileChooser fc;
	private File selectedFile;
	
	
	public SavePopup (int x, int y) {
        popup = new Popup();
        popup.setX(x);
        popup.setY(y);
        
		textA = new TextArea("test");
        popup.getContent().addAll(textA);
        textA.setMinWidth(80);
        textA.setMinHeight(50);
		
		fc = new FileChooser();
	}
	
	public File promptUserSave() {
		File selectedFile = fc.showSaveDialog(null);
		
		return selectedFile;
	}
	
	public File promptUserOpen() {
		File selectedFile = fc.showOpenDialog(null);
		
		return selectedFile;
	}
	
	// getter and setter methods
	
	public Popup getPopup() {
		return popup;
	}
	
	
	
}
