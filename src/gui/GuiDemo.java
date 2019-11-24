package gui;

import javafx.collections.FXCollections;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;

public class GuiDemo<toReturn> extends Application {
    private Controller theController;
    private BorderPane root;
    private DescriptionPopup descriptionPane;
    private Stage primaryStage;
	private String selectedString;
	private String selectedDoor;
	
	private TextArea textbox; // textbox in the center of the program
	private ArrayList<String> myDoors;
	ListView doorList;

    /*a call to start replaces a call to the constructor for a JavaFX GUI*/
    @Override
    public void start(Stage assignedStage) {
        /* Initializing instance variables */
        theController = new Controller(this);
        primaryStage = assignedStage;
		
		// 
		selectedString = "";
		selectedDoor = "";
		
		myDoors = new ArrayList<>();
		
        /* Border Panes have  top, left, right, center and bottom sections */
        root = setUpRoot();
        descriptionPane = new DescriptionPopup(200, 300, "please click on a space");
        Scene scene = new Scene(root, 600, 300);
        primaryStage.setTitle("Dungeon");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private BorderPane setUpRoot() {
		// borderpane temp
        BorderPane temp = new BorderPane();
		
		// bottom
        temp.setTop(new Label("The name or identifier of the thing below"));
		
		// right
        Node buttons = setButtonPanel();  //separate method for the left section
        temp.setRight(buttons);
		
		// left
        ObservableList<String> hydraList = FXCollections.observableArrayList(theController.getNameList());
        temp.setLeft(createListView(hydraList));
//        GridPane room = new ChamberView(4,4);

		// center
		textbox = new TextArea();
        temp.setCenter(textbox);
        return temp;
    }

    private Node createListView(ObservableList<String> spaces){
        ListView temp = new ListView<String>(spaces);
        temp.setPrefWidth(100);
        temp.setPrefHeight(150);
		
		// what happens when the thing is selected
        temp.setOnMouseClicked((MouseEvent event)->{
            System.out.println("clicked on " + temp.getSelectionModel().getSelectedItem());
			selectedString = (String) temp.getSelectionModel().getSelectedItem();
			
			// call the controller to ask for what chamber that is and its description
			theController.setCurrentSpace(selectedString);
			descriptionPane.setText(theController.getNewDescription());
			textbox.setText(theController.getNewDescription());
			
			// set up the doors
			myDoors = theController.getDoors();
			
        });

        return temp;
    }

	// button panel on the right side
    private Node setButtonPanel() {
        /*this method should be broken down into even smaller methods, maybe one per button*/
        VBox temp = new VBox();
        
		
		
		
		
		// edit chamber
        Button showButton = createButton("Edit Space", "-fx-background-color: #FFFFFF; ");
        showButton.setOnAction((ActionEvent event) -> {
            descriptionPane.getPopup().show(primaryStage);
        });
        temp.getChildren().add(showButton);
		
		
		
		
		
        // save and close chamber edit box
        Button hideButton = createButton("Save Edits", "-fx-background-color: #FFFFFF; ");
        hideButton.setOnAction((ActionEvent event) -> {
            descriptionPane.getPopup().hide();
			System.out.println(descriptionPane.getText());
        });
        temp.getChildren().add(hideButton);
        
		
		
		
		
		// setup the doorlist listview
		myDoors.add("Hello 1");
		myDoors.add("Door 23");
		
		ObservableList<String> names = FXCollections.observableArrayList(myDoors);
		
		doorList = new ListView<String>(names);
		doorList.setPrefWidth(100);
		
		// what happens when the thing is selected
        doorList.setOnMouseClicked((MouseEvent event)->{
            System.out.println("cl icked on " + doorList.getSelectionModel().getSelectedItem());
			selectedDoor = (String) doorList.getSelectionModel().getSelectedItem();

        });
		
		temp.getChildren().add(doorList);





		return temp;
    }

	// generic button maker
    private Button createButton(String text, String format) {
        Button btn = new Button();
        btn.setText(text);
        return btn;
    }

	/**
	 * Weird second main method that I dont fully understand how it works.
	 * @param agrs, the arguments for the program.
	 */
    public static void main(String[] args) {
        launch(args);
    }

}
